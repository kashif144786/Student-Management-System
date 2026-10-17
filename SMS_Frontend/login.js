// ====== Hardcoded credentials, matching LoginFrame.java ======
const VALID_USERNAME = "admin";
const VALID_PASSWORD = "1234";
// ================================================================

const form = document.getElementById("login-form");
const errorText = document.getElementById("error-text");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value;

  if (username === VALID_USERNAME && password === VALID_PASSWORD) {
    sessionStorage.setItem("sms_logged_in", "true");
    window.location.href = "dashboard.html";
  } else {
    errorText.textContent = "Invalid username or password.";
  }
});