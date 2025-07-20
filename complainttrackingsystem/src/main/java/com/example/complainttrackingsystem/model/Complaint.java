package com.example.complainttrackingsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Complaint {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	private String description;
	private String status; // e.g :  "OPEN", "IN_PROGRESS", "RESOLVED"
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Complaint()
	{
		super();
	}
	public Complaint(Long id, String subject,String description,String status)
	{
		super();
		this.id=id;
		this.subject=subject;
		this.description=description;
		this.status=status;
		
		}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}