// Shared guard for every protected page.
// Mirrors the hardcoded login in your Swing app's LoginFrame.java (admin / 1234)

function requireAuth() {
  if (sessionStorage.getItem("sms_logged_in") !== "true") {
    window.location.href = "login.html";
  }
}

function logout() {
  sessionStorage.removeItem("sms_logged_in");
  window.location.href = "login.html";
}