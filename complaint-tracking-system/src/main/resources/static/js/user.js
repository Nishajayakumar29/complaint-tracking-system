// Get user email from session or page context
const userEmail = sessionStorage.getItem("email"); // make sure this is set after login

function createComplaint() {
  const complaintText = document.getElementById("complaintText").value.trim();
  if (!complaintText) {
    alert("Please enter your complaint.");
    return;
  }

  const complaintData = {
    email: userEmail,
    complaintText: complaintText
  };

  fetch("/api/complaints/create", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(complaintData)
  })
  .then(async response => {
    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || "Failed to submit complaint.");
    }
    alert("Complaint submitted successfully.");
    document.getElementById("complaintText").value = "";
    closeModal();
  })
  .catch(error => {
    console.error("Submit Error:", error);
    alert("Error submitting complaint: " + error.message);
  });

}

function viewMyComplaints() {
  // fetch(`/api/complaints/user?email=${encodeURIComponent(userEmail)}`)
  fetch(`/api/complaints/user/${encodeURIComponent(userEmail)}`)  
  .then(response => response.json())
   
	.then(data => {
	  if (!Array.isArray(data)) {
	    throw new Error("Unexpected response format.");
	  }

	  const tbody = document.getElementById("complaintsTableBody");
	  if (!tbody) return;
	  tbody.innerHTML = "";
	  

	  document.getElementById("complaintsTable").style.display = "table";

	  data.forEach(complaint => {
	    const row = document.createElement("tr");
	    row.innerHTML = `
	      <td>${complaint.id}</td>
	     
	      <td>${complaint.complaintText}</td>
	      <td>${complaint.status}</td>
	    `;
	    tbody.appendChild(row);
	  });
	})

    .catch(error => {
      console.error("error loading complaints",error);
      alert("Error fetching complaints.");
    });
}


function openModal() {
  document.getElementById("complaintModal").style.display = "flex";
}

function closeModal() {
  document.getElementById("complaintModal").style.display = "none";
}

function viewComplaints() {
  viewMyComplaints();
}

