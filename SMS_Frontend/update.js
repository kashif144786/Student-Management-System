requireAuth();

// ====== Your backend's real URL and port ======
const API_BASE_URL = "http://localhost:8080/api/students";
// =================================================

const lookupIdInput = document.getElementById("lookup-id");
const loadBtn = document.getElementById("load-btn");
const lookupError = document.getElementById("lookup-error");
const updateForm = document.getElementById("update-form");
const successText = document.getElementById("success-text");

const nameInput = document.getElementById("name");
const ageInput = document.getElementById("age");
const courseInput = document.getElementById("course");
const emailInput = document.getElementById("email");

let currentId = null;

loadBtn.addEventListener("click", async () => {
  const id = lookupIdInput.value.trim();
  lookupError.textContent = "";
  successText.textContent = "";

  if (!id) {
    lookupError.textContent = "Enter a Student ID first.";
    return;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/${id}`);
    if (!response.ok) {
      updateForm.classList.add("hidden");
      lookupError.textContent = "No student found with that ID.";
      return;
    }

    const student = await response.json();
    currentId = student.id;

    nameInput.value = student.name;
    ageInput.value = student.age;
    courseInput.value = student.course;
    emailInput.value = student.email;

    updateForm.classList.remove("hidden");
  } catch (error) {
    lookupError.textContent = "Could not reach the server.";
    console.error(error);
  }
});

updateForm.addEventListener("submit", async (e) => {
  e.preventDefault();

  const payload = {
    name: nameInput.value.trim(),
    age: Number(ageInput.value),
    course: courseInput.value.trim(),
    email: emailInput.value.trim()
  };

  try {
    const response = await fetch(`${API_BASE_URL}/${currentId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });
    if (!response.ok) throw new Error("Update failed");

    successText.textContent = "Student updated successfully.";
  } catch (error) {
    lookupError.textContent = "Could not save changes.";
    console.error(error);
  }
});