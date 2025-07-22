package com.example.complaint_tracking_system.controller;

import com.example.complaint_tracking_system.model.Complaint;
import com.example.complaint_tracking_system.model.User;
import com.example.complaint_tracking_system.service.ComplaintService;
import com.example.complaint_tracking_system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.complaint_tracking_system.dao.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*") // Allow frontend requests
public class ComplaintController {

	
	
    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private AuthService AuthService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ComplaintRepository complaintRepository;

    /**
     * Create a new complaint by a user
     */
    @PostMapping("/create")
    public ResponseEntity<?> createComplaint(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String complaintText = payload.get("complaintText");

        if (email == null || complaintText == null) {
            return ResponseEntity.badRequest().body("Email or complaint text missing");
        }

        User user = userRepository.findByEmail(email); // or however you fetch users
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Complaint complaint = new Complaint();
        complaint.setUser(user);
        complaint.setComplaintText(complaintText);
        complaint.setStatus("Pending");

        complaintRepository.save(complaint);
        return ResponseEntity.ok("Complaint created successfully");
    }


    /**
     * Get complaints by user email (view my complaints)
     */
    @GetMapping("/user/{email}")
    public List<Complaint> getUserComplaints(@PathVariable String email) {
        return complaintService.getComplaintsByUserEmail(email);
    }

    /**
     * Get status of complaints by user email
     */
    @GetMapping("/status/{email}")
    public List<Complaint> getComplaintStatus(@PathVariable String email) {
        return complaintService.getComplaintsByUserEmail(email);
    }

    /**
     * Admin - Get all complaints
     */
    @GetMapping("/all")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    /**
     * Admin - Update complaint status
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateComplaintStatus(@PathVariable Long id, @RequestBody Map<String, String> body ) {
        String status = body.get("status");
        boolean updated = complaintService.updateStatus(id, status);
        return updated ? ResponseEntity.ok("Status updated.") : ResponseEntity.badRequest().body("Complaint not found.");
    }

    /**
     * Admin - View only completed complaints
     */
    @GetMapping("/completed")
    public List<Complaint> getCompletedComplaints() {
    	return complaintService.getComplaintsByStatus("Completed");

    }
}
