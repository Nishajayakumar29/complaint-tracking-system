 complaint-tracking-system

The Complaint Tracking System is a web-based full-stack application developed to manage and resolve user complaints in a structured, secure, and efficient manner. The primary objective of the system is to provide a user-friendly platform where users—such as the general public, employees, or customers—can easily raise complaints, while administrators can effectively monitor, update, and resolve them.

1. How It Works
-User Registration & Login with OTP:
-New users can sign up using their email and password. During login, an OTP is sent to the registered email. Only upon entering the correct OTP, the user is authenticated and granted access to the system.
-Role-Based Dashboard Redirection:
-If the login credentials belong to an admin, the user is redirected to the Admin Dashboard.
-If the credentials belong to a regular user, they are redirected to the User Dashboard.

2. Admin Functionalities
-View all registered complaints.
-Update the status of complaints (e.g., Incomplete, Ongoing, Completed).
-Delete complaints that are marked as completed.

3. User Functionalities
-Create and submit new complaints.
-View all complaints submitted by the user.
-Check the status of each complaint in real-time.

4. Tech Stack
-Backend: Java Spring Boot (REST API), Spring Security, JavaMailSender (OTP)
-Frontend: HTML, CSS, JavaScript
-Database: MySQL

