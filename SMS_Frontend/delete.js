requireAuth();

// ====== Your backend's real URL and port ======
const API_BASE_URL = "http://localhost:8080/api/students";
// =================================================

const lookupIdInput = document.getElementById("lookup-id");
const findBtn = document.getElementById("find-btn");
const lookupError = document.getElementById("lookup-error");
const studentDetail = document.getElementById("student-detail");
const deleteActions = document.getElementById("delete-actions");
const deleteBtn = document.getElementById("delete-btn");
const successText = document.getElementById("success-text");

let currentId = null;

findBtn.addEventListener("click", async () => {
  const id = lookupIdInput.value.trim();
  lookupError.textContent = "";
  successText.textContent = "";
  studentDetail.classList.add("hidden");
  deleteActions.classList.add("hidden");

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
    currentId = student.id;

    document.getElementById("detail-id").textContent = student.id;
    document.getElementById("detail-name").textContent = student.name;
    document.getElementById("detail-age").textContent = student.age;
    document.getElementById("detail-course").textContent = student.course;
    document.getElementById("detail-email").textContent = student.email;

    studentDetail.classList.remove("hidden");
    deleteActions.classList.remove("hidden");
  } catch (error) {
    lookupError.textContent = "Could not reach the server.";
    console.error(error);
  }
});

deleteBtn.addEventListener("click", async () => {
  if (!confirm("Delete this student? This cannot be undone.")) return;

  try {
    const response = await fetch(`${API_BASE_URL}/${currentId}`, { method: "DELETE" });
    if (!response.ok) throw new Error("Delete failed");

    successText.textContent = "Student deleted successfully.";
    studentDetail.classList.add("hidden");
    deleteActions.classList.add("hidden");
    lookupIdInput.value = "";
  } catch (error) {
    lookupError.textContent = "Could not delete student.";
    console.error(error);
  }
});