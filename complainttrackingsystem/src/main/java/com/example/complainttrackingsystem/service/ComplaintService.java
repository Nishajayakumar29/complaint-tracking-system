package com.example.complainttrackingsystem.service;

import com.example.complainttrackingsystem.model.Complaint;
import java.util.List;

// Interface to define complaint-related operations
public interface ComplaintService {

    // INSERT/UPDATE: Save a new complaint or update an existing one
    Complaint saveComplaint(Complaint complaint);

    // SELECT: Get complaint by ID
    Complaint getComplaintById(Long id);

    // SELECT: Get all complaints
    List<Complaint> getAllComplaints();

    // DELETE: Delete a complaint by ID
    void deleteComplaint(Long id);

    // SELECT: Get all complaints filed by a specific user
    List<Complaint> getComplaintsByUserId(Long userId);
}
