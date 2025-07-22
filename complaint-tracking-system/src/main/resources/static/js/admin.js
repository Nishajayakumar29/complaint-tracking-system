// Fetch all complaints
function fetchAllComplaintsUser(){
	fetch('/api/complaints/all')
	    .then(res => res.json())
	   .then(data => displayComplaintsUser(data))
	    .catch(err => alert("Error fetching complaints: " + err.message));
	
}
function displayComplaintsUser(complaints) {
  const table = document.getElementById("complaintsTableBody");
  table.innerHTML = "";
complaints.forEach(c => {
    const row = document.createElement("tr");

    row.innerHTML = `
      <td>${c.id}</td>
	  <td>${c.user?.name || "N/A"}</td>
	  <td>${c.user?.email || "N/A"}</td>
	  <td>${c.complaintText || "N/A"}</td>
	  <td>${c.status}</td>
    `;
    table.appendChild(row);
  });
}

function fetchAllComplaints() {
  fetch('/api/complaints/all')
    .then(res => res.json())
    .then(data => displayComplaints(data))
    .catch(err => alert("Error fetching complaints: " + err.message));
}

// Display complaints in a table
 function displayComplaints(complaints) {
	
  const table = document.getElementById("complaintsTableBody");
  if (!table) return; // Exit early if element doesn't exist

    table.innerHTML = "";
   
  complaints.forEach(c => {
    const row = document.createElement("tr");

    row.innerHTML = `
      <td>${c.id}</td>
	  <td>${c.user?.name || "N/A"}</td>
	  <td>${c.user?.email || "N/A"}</td>
	  <td>${c.complaintText || "N/A"}</td>
	  <td>${c.status}</td>
	  <td>
	    <button onclick="editComplaint(${c.id})">Edit</button>
	  </td>
    `;
    table.appendChild(row);
  });
}

// Update complaint status
function updateComplaintStatus(id, newStatus) {
    fetch(`/api/complaints/${id}/status`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ status: newStatus })
    })
    .then(response => {
        if (response.ok) {
            alert('Status updated successfully');
            fetchAllComplaints(); // refresh
        } else {
            alert('Error: Failed to update status');
        }
    })
    .catch(error => console.error('Error:', error));
}

// View completed complaints
function fetchCompletedComplaints() {
  fetch('/api/complaints/completed')
    .then(res => res.json())
    .then(data => displayCompleted(data))
    .catch(err => alert("Error: " + err.message));
}

// Display only completed complaints

function displayCompleted(complaints) {
  const table = document.getElementById("completedTableBody");
 /* if (!table) {
    console.error("Element with ID 'completedTableBody' not found.");
    return;
  }

  table.innerHTML = "";*/
  if (table) {
      table.innerHTML = "";
      // build table rows
    } else {
      // Optional: silence or debug log
      console.warn("complaintsTableBody not found on this page.");
    }

  complaints.forEach(c => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${c.id}</td>
      <td>${c.user?.name || "N/A"}</td>
      <td>${c.user?.email || "N/A"}</td>
      <td>${c.complaintText || "N/A"}</td>
      <td>${c.status}</td>
	 
    `;
    table.appendChild(row);
  });
}




function editComplaint(id) {
  window.location.href = `/edit-complaint.html?id=${id}`;
}
// logout for the admin 

function confirmAdminLogout() {
    if (confirm("Are you sure you want to logout?")) {
      const email = sessionStorage.getItem("email");

      fetch(`http://localhost:8081/api/auth/logout/${email}`, {
        method: 'DELETE',
      })
      .then(response => response.text())
      .then(message => {
        alert(message);
        sessionStorage.clear();
        window.location.href = "login.html";
      })
      .catch(err => {
        console.error("Logout error:", err);
        alert("Error logging out.");
      });
    }
}

window.onload = function () {
	  fetchAllComplaints();
	};



  


