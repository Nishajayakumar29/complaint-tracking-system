package com.example.complainttrackingsystem.dto;

public class AuthResponse {
    private String token;

    public AuthResponse() 
    {
    	super();
    }

    public AuthResponse(String token) 
    {
    	super();
        this.token = token;
    }

    // Getter and setter
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
