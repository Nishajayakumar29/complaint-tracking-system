let sentOtp = "";

function sendOtp() {
  const email = document.getElementById("email").value.trim();
  const role = document.getElementById("role").value;

  if (!email || !role) {
    alert("Please fill in both email and role.");
    return;
  }

  fetch("/api/auth/send-otp", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email: email, role: role })
  })
  .then(res => res.json()) // ✅ Parse response as JSON
  .then(data => {
    if (data.status === "success") {
      sentOtp = data.otp;
      document.getElementById("otp-display").innerText = `Your OTP: ${sentOtp}`;
      document.getElementById("otp-box").style.display = "block";
    } else {
      alert("Error: " + data.message);
    }
  })
  .catch(err => {
    alert("Error: " + err.message);
  });
}


function verifyOtp() {
  const email = document.getElementById("email").value.trim();
  const role = document.getElementById("role").value;
  const userOtp = document.getElementById("otp").value.trim();

  if (!userOtp) {
    alert("Please enter OTP");
    return;
  }

  fetch("/api/auth/verify-otp", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email: email, otp: userOtp, role: role })
  })
  .then(res => res.json())
  .then(data => {
    if (data.status === "success") {
		sessionStorage.setItem("email", email);
      if (role === "user") {
        window.location.href = "user-dashboard.html";
      } else if (role === "admin") {
        window.location.href = "admin-dashboard.html";
      }
    } else {
      alert("OTP verification failed: " + data.message);
    }
  })
  .catch(err => {
    alert("Error: " + err.message);
  });
}
function goToHome() {
    window.location.href = "index.html";
  }
