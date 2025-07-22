package com.example.complaint_tracking_system.service;

import com.example.complaint_tracking_system.model.Complaint;
import com.example.complaint_tracking_system.model.User;
import com.example.complaint_tracking_system.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Save a new complaint
     */
    public void saveComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    /**
     * Get complaints by user email
     */
    public List<Complaint> getComplaintsByUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? complaintRepository.findByUser(user) : List.of();
    }

    /**
     * Get all complaints (admin)
     */
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    /**
     * Update status of a complaint by ID
     */
    public boolean updateStatus(Long id, String status) {
        return complaintRepository.findById(id).map(complaint -> {
            complaint.setStatus(status);
            complaintRepository.save(complaint);
            return true;
        }).orElse(false);
    }

    /**
     * Get complaints with a specific status (e.g., completed)
     */
    public List<Complaint> getComplaintsByStatus(String status) {
        return complaintRepository.findByStatus(status);
    }
}
