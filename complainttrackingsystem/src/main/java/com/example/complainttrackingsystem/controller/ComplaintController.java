package com.example.complainttrackingsystem.controller;

import com.example.complainttrackingsystem.model.Complaint;
import com.example.complainttrackingsystem.model.User;
import com.example.complainttrackingsystem.service.ComplaintService;
import com.example.complainttrackingsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private UserService userService;

    // ✅ Create a new complaint (linked to logged-in user)
    @PostMapping("/create")
    public ResponseEntity<?> createComplaint(@RequestBody Complaint complaint, Authentication authentication) {
        System.out.println("User role: " + authentication.getAuthorities()); // Debug print

        String userEmail = authentication.getName(); // Extract email from JWT
        User user = userService.findByEmail(userEmail);

        complaint.setUser(user); // Link complaint to logged-in user
        complaint.setStatus("OPEN"); // Default status

        Complaint savedComplaint = complaintService.saveComplaint(complaint);
        return ResponseEntity.ok(savedComplaint);
    }

    // ✅ Get all complaints of logged-in user
    @GetMapping("/my")
    public ResponseEntity<List<Complaint>> getMyComplaints(Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userService.findByEmail(userEmail);

        List<Complaint> complaints = complaintService.getComplaintsByUserId(user.getId());
        return ResponseEntity.ok(complaints);
    }

    // ✅ Admin: Get all complaints
    @GetMapping("/all")
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        List<Complaint> complaints = complaintService.getAllComplaints();
        return ResponseEntity.ok(complaints);
    }

    // ✅ FIXED: Get complaint by ID — now no conflict with "/create"
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getComplaintById(@PathVariable Long id, Authentication authentication) {
        Complaint complaint = complaintService.getComplaintById(id);
        if (complaint == null) {
            return ResponseEntity.notFound().build();
        }

        // Find logged-in user
        User loggedInUser = userService.findByEmail(authentication.getName());

        // Allow if owner or admin
        if (complaint.getUser().getId().equals(loggedInUser.getId()) 
                || "ROLE_ADMIN".equals(loggedInUser.getRole())) {
            return ResponseEntity.ok(complaint);
        } else {
            return ResponseEntity.status(403).body("You are not authorized to view this complaint.");
        }
    }

    // ✅ Delete complaint by ID (if owner or admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComplaint(@PathVariable Long id, Authentication authentication) {
        Complaint complaint = complaintService.getComplaintById(id);
        if (complaint == null) {
            return ResponseEntity.notFound().build();
        }

        String userEmail = authentication.getName();
        User loggedInUser = userService.findByEmail(userEmail);

        // Only owner or admin can delete
        if (complaint.getUser().getId().equals(loggedInUser.getId()) || loggedInUser.getRole().equals("ROLE_ADMIN")) {
            complaintService.deleteComplaint(id);
            return ResponseEntity.ok("Complaint deleted successfully.");
        }

        return ResponseEntity.status(403).body("You are not authorized to delete this complaint.");
    }

    // ✅ Update complaint status (admin only)
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status, Authentication authentication) {
        Complaint complaint = complaintService.getComplaintById(id);
        if (complaint == null) {
            return ResponseEntity.notFound().build();
        }

        User loggedInUser = userService.findByEmail(authentication.getName());
        if (!"ROLE_ADMIN".equals(loggedInUser.getRole())) {
            return ResponseEntity.status(403).body("You are not authorized to update status.");
        }

        complaint.setStatus(status);
        complaintService.saveComplaint(complaint);

        return ResponseEntity.ok("Complaint status updated to: " + status);
    }
}
