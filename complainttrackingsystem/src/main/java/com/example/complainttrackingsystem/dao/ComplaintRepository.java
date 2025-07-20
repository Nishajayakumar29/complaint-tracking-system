package com.example.complainttrackingsystem.dao;

import com.example.complainttrackingsystem.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUserId(Long userId); // To get complaints of a user
}
