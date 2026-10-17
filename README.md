# Student Management System

A full-stack Student Management System built with Java and MySQL. It includes a Java Swing desktop app and a web frontend, both managing student records (add, view, search, update, delete).

## Features

- Admin login
- Add a new student
- View all students
- Search for a student
- Update student details
- Delete a student
- Dashboard for quick navigation

## Tech Stack

| Part      | Technology                          |
|-----------|-------------------------------------|
| Backend   | Java, JDBC                          |
| Desktop UI| Java Swing                          |
| Frontend  | HTML, CSS, JavaScript               |
| Database  | MySQL                               |
| IDEs      | Eclipse (backend), VS Code (frontend) |

## Project Structure

```
JAVA_PROJECT/
├── StudentManagementSystem/   # Java backend + Swing UI
│   └── src/com/sms/
│       ├── api/      # API server and JSON utilities
│       ├── dao/      # Database access (StudentDAO)
│       ├── main/     # Entry point (Main.java)
│       ├── model/    # Student model
│       ├── ui/       # Swing screens
│       └── util/     # DBConnection
└── SMS_Frontend/              # Web frontend (HTML/CSS/JS)
```

## Screenshots

![Login](screenshots/login.png)
![Dashboard](screenshots/dashboard.png)
![Student List](screenshots/list.png)

## Setup and Run

### Prerequisites

- JDK 8 or later
- MySQL Server
- MySQL Connector/J (JDBC driver) added to the project's build path
- Eclipse (or any Java IDE)

### 1. Clone the repository

```bash
git clone https://github.com/kashif144786/Student-Management-System
```

### 2. Set up the database

Create the database and table in MySQL:

```sql
CREATE DATABASE sms_db;
USE sms_db;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    course VARCHAR(50),
    email VARCHAR(100)
);

-- Optional sample data (dummy values)
INSERT INTO students VALUES (1, 'Rahul Sharma', 20, 'CSE', 'rahul@example.com');
INSERT INTO students VALUES (2, 'Priya Das', 21, 'IT', 'priya@example.com');
```

### 3. Configure the database connection

Open `StudentManagementSystem/src/com/sms/util/DBConnection.java` and replace the placeholder with your MySQL password:

```java
"root", "your_password_here"
```

### 4. Run the backend

Import `StudentManagementSystem` into Eclipse (**File → Import → Existing Projects into Workspace**), then run `Main.java`.

### 5. Run the frontend

Open `SMS_Frontend/index.html` in your browser (or use the VS Code Live Server extension). Make sure the backend API server is running first.

## Default Login

| Username | Password |
|----------|----------|
| admin    | 1234     |

## Author

MD KASHIF RAZA ANSARI – [GitHub](https://github.com/kashif144786)