requireAuth();

// ====== Your backend's real URL and port ======
const API_BASE_URL = "http://localhost:8080/api/students";
// =================================================

const lookupIdInput = document.getElementById("lookup-id");
const searchBtn = document.getElementById("search-btn");
const lookupError = document.getElementById("lookup-error");
const studentDetail = document.getElementById("student-detail");

searchBtn.addEventListener("click", async () => {
  const id = lookupIdInput.value.trim();
  lookupError.textContent = "";
  studentDetail.classList.add("hidden");

  if (!id) {
    lookupError.textContent = "Enter a Student ID first.";
    return;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/${id}`);
    if (!response.ok) {
      lookupError.textContent = "No student found with that ID.";
      return;
    }

    const student = await response.json();

    document.getElementById("detail-id").textContent = student.id;
    document.getElementById("detail-name").textContent = student.name;
    document.getElementById("detail-age").textContent = student.age;
    document.getElementById("detail-course").textContent = student.course;
    document.getElementById("detail-email").textContent = student.email;

    studentDetail.classList.remove("hidden");
  } catch (error) {
    lookupError.textContent = "Could not reach the server.";
    console.error(error);
  }
});