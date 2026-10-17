requireAuth();

// ====== Your backend's real URL and port ======
const API_BASE_URL = "http://localhost:8080/api/students";
// =================================================

const form = document.getElementById("student-form");
const heading = document.getElementById("form-heading");
const subtitle = document.getElementById("form-subtitle");
const submitBtn = document.getElementById("submit-btn");

const nameInput = document.getElementById("name");
const ageInput = document.getElementById("age");
const courseInput = document.getElementById("course");
const emailInput = document.getElementById("email");

const params = new URLSearchParams(window.location.search);
const studentId = params.get("id");

if (studentId) {
  heading.textContent = "Edit Student";
  subtitle.textContent = "Update the details below and save.";
  submitBtn.textContent = "Update Student";
  loadStudent(studentId);
}

async function loadStudent(id) {
  try {
    const response = await fetch(`${API_BASE_URL}/${id}`);
    if (!response.ok) throw new Error("Failed to fetch student");
    const student = await response.json();

    nameInput.value = student.name ?? "";
    ageInput.value = student.age ?? "";
    courseInput.value = student.course ?? "";
    emailInput.value = student.email ?? "";
  } catch (error) {
    alert("Could not load student details.");
    console.error(error);
  }
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const payload = {
    name: nameInput.value.trim(),
    age: Number(ageInput.value),
    course: courseInput.value.trim(),
    email: emailInput.value.trim()
  };

  const url = studentId ? `${API_BASE_URL}/${studentId}` : API_BASE_URL;
  const method = studentId ? "PUT" : "POST";

  try {
    const response = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });
    if (!response.ok) throw new Error("Failed to save student");

    window.location.href = "index.html";
  } catch (error) {
    alert("Could not save student.");
    console.error(error);
  }
});