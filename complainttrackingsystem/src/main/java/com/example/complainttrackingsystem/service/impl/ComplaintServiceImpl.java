package com.example.complainttrackingsystem.service.impl;

import com.example.complainttrackingsystem.dao.ComplaintRepository;
import com.example.complainttrackingsystem.model.Complaint;
import com.example.complainttrackingsystem.service.ComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// This class contains business logic for handling complaints
@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository r;

    // INSERT/UPDATE: Save or update a complaint in the database
    @Override
    public Complaint saveComplaint(Complaint complaint) {
        return r.save(complaint); // SQL: INSERT or UPDATE
    }

    // SELECT: Fetch a complaint by its ID
    @Override
    public Complaint getComplaintById(Long id) {
        return r.findById(id).orElse(null); // SQL: SELECT * FROM complaints WHERE id=?
    }

    // SELECT: Fetch all complaints
    @Override
    public List<Complaint> getAllComplaints() {
        return r.findAll(); // SQL: SELECT * FROM complaints
    }

    // DELETE: Delete a complaint by ID
    @Override
    public void deleteComplaint(Long id) {
        r.deleteById(id); // SQL: DELETE FROM complaints WHERE id=?
    }

    // SELECT: Get all complaints submitted by a particular user (based on user_id foreign key)
    @Override
    public List<Complaint> getComplaintsByUserId(Long userId) {
        return r.findByUserId(userId); // SQL: SELECT * FROM complaints WHERE user_id=?
    }
}
