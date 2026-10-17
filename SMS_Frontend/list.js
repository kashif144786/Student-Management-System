requireAuth();

// ====== Your backend's real URL and port ======
const API_BASE_URL = "http://localhost:8080/api/students";
// =================================================

const tableBody = document.getElementById("student-table-body");
const rowCountEl = document.getElementById("row-count");
const searchInput = document.getElementById("search-input");

let students = [];

async function loadStudents() {
  try {
    const response = await fetch(API_BASE_URL);
    if (!response.ok) throw new Error("Failed to fetch students");
    students = await response.json();
    renderTable(students);
  } catch (error) {
    tableBody.innerHTML = `<tr><td colspan="6">Could not load students. Is ApiServer.java running?</td></tr>`;
    console.error(error);
  }
}

function renderTable(list) {
  if (list.length === 0) {
    tableBody.innerHTML = `<tr><td colspan="6">No students found.</td></tr>`;
    rowCountEl.textContent = "Showing 0 students";
    return;
  }

  tableBody.innerHTML = list.map(student => `
    <tr>
      <td>${student.id}</td>
      <td>${student.name}</td>
      <td>${student.age}</td>
      <td>${student.course}</td>
      <td>${student.email}</td>
      <td class="col-actions">
        <a href="form.html?id=${student.id}" class="btn btn-edit">Edit</a>
        <button class="btn btn-delete" data-id="${student.id}">Delete</button>
      </td>
    </tr>
  `).join("");

  rowCountEl.textContent = `Showing ${list.length} student${list.length === 1 ? "" : "s"}`;
}

tableBody.addEventListener("click", async (e) => {
  if (!e.target.classList.contains("btn-delete")) return;

  const id = e.target.dataset.id;
  if (!confirm("Delete this student?")) return;

  try {
    const response = await fetch(`${API_BASE_URL}/${id}`, { method: "DELETE" });
    if (!response.ok) throw new Error("Failed to delete student");
    loadStudents();
  } catch (error) {
    alert("Could not delete student.");
    console.error(error);
  }
});

searchInput.addEventListener("input", () => {
  const query = searchInput.value.toLowerCase();
  const filtered = students.filter(s =>
    s.name.toLowerCase().includes(query) || s.email.toLowerCase().includes(query)
  );
  renderTable(filtered);
});

loadStudents();