 complaint-tracking-system

The Complaint Tracking System is a web-based full-stack application developed to manage and resolve user complaints in a structured, secure, and efficient manner. The primary objective of the system is to provide a user-friendly platform where users—such as the general public, employees, or customers—can easily raise complaints, while administrators can effectively monitor, update, and resolve them.
- Features
a. User Features
* User Registration and Login
* Submit a new complaint
* View all submitted complaints with their current status

b. Admin Features
* Admin login
* View all user complaints
* Update the status of any complaint (Not Started, In Progress, Completed)
* Filter/view completed complaints
* Edit complaint status from a dedicated page

- Technologies Used
a. Backend
* Java
* Spring Boot
* Spring Security JavaMailSender (OTP)
* Spring Data JPA
* REST API
*  MySQL Database

b. Frontend
* HTML5
* CSS3
* JavaScript 


# How It Works (Short Overview)
1.User Registration & Login

* A user can register with name, email, and password.

* After login, the user is redirected to their dashboard.

2. User Dashboard

* Users can submit complaints (like issues or requests).

* They can also view the status of their previously submitted complaints.

3. Admin Login & Dashboard

- Admin logs in using admin credentials.

- Admin can:

* View all complaints

* Update status (Not Started, In Progress, Completed)

* Edit any complaint status

* View completed complaints only

4. Status Updates

* Admin clicks "Edit" → Goes to a separate edit page

* Selects a new status and updates it via a REST API call

5. Logout

* Both user and admin can securely log out of the system



