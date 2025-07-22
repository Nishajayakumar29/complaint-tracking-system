package com.example.complaint_tracking_system.dao;

import com.example.complaint_tracking_system.model.Complaint;
import com.example.complaint_tracking_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // Fetch all complaints by user
    List<Complaint> findByUser(User user);

    // Fetch all complaints by status
    List<Complaint> findByStatus(String status);
}
