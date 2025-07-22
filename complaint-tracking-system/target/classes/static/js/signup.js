function submitSignup(event) {
  event.preventDefault();

  const name = document.getElementById("name").value.trim();
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();
  const role = document.getElementById("role").value;

  if (!name || !email || !password || !role) {
    showMessage("All fields are required", false);
    return;
  }

  const signupData = {
    name: name,
    email: email,
    password: password,
    role: role.toUpperCase()
  };

  fetch("/api/auth/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(signupData)
  })
  .then(res => {
    if (res.ok) return res.text();
    else throw new Error("Signup failed. Email may already exist.");
  })
  .then(message => {
    showMessage("Signup successful! Please login.", true);
    document.getElementById("signup-form").reset();
  })
  .catch(error => {
    showMessage(error.message || "Signup error", false);
  });
}

function showMessage(msg, isSuccess) {
  const msgDiv = document.getElementById("message");
  msgDiv.innerText = msg;
  msgDiv.style.color = isSuccess ? "green" : "red";
}

function goToLogin() {
    window.location.href = "login.html";
}