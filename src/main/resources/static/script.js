const API_URL = "/api";

const employeeTableBody =
    document.getElementById("employeeTableBody");

const employeeForm =
    document.getElementById("employeeForm");


// =========================
// GET ALL EMPLOYEES
// =========================

async function loadEmployees() {

    try {

        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to fetch employees");
        }

        const employees = await response.json();

        employeeTableBody.innerHTML = "";

        employees.forEach(employee => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.roll_no}</td>
                <td>${employee.department}</td>
                <td>${employee.email}</td>

                <td>
                    <button onclick="deleteEmployee(${employee.id})">
                        Delete
                    </button>
                </td>
            `;

            employeeTableBody.appendChild(row);
        });

    } catch (error) {

        console.error(error);
    }
}


// =========================
// POST - ADD EMPLOYEE
// =========================

employeeForm.addEventListener("submit", async function(event) {

    event.preventDefault();

    const employee = {
        name: document.getElementById("name").value,
        roll_no: document.getElementById("roll_no").value,
        department: document.getElementById("department").value,
        email: document.getElementById("email").value
    };

    console.log("Sending employee:", employee);

    try {

        const response = await fetch(API_URL, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(employee)
        });

        console.log("POST status:", response.status);

        const result = await response.text();

        console.log("POST response:", result);

        if (!response.ok) {
            throw new Error(
                "HTTP " + response.status + " - " + result
            );
        }

        alert("Employee added successfully!");

        employeeForm.reset();

        loadEmployees();

    } catch (error) {

        console.error("POST ERROR:", error);

        alert(error.message);
    }
});

// =========================
// DELETE EMPLOYEE
// =========================

async function deleteEmployee(id) {

    if (!confirm("Are you sure?")) {
        return;
    }


    try {

        const response = await fetch(`${API_URL}/${id}`, {

            method: "DELETE"
        });


        if (!response.ok) {
            throw new Error("Failed to delete employee");
        }

        alert("Employee deleted successfully!");

        loadEmployees();

    } catch (error) {

        console.error(error);

        alert("Could not delete employee");
    }
}


// =========================
// LOAD DATA
// =========================

loadEmployees();