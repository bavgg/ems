# Employment Management System

## Overview

The **Employment Management System** is a desktop application built using JavaFX and SQLite3 that helps organizations manage employee records. The system allows the user to create, read, update, and delete (CRUD) employee information efficiently. This project is aimed at simplifying human resource management tasks, such as storing employee details, tracking their employment status, and managing salaries.

## Features

- **Add Employee**: Add new employees with their details such as name, position, department, and salary.
- **Update Employee**: Modify existing employee information.
- **Delete Employee**: Remove an employee from the database.
- **View Employee List**: Display a list of all employees.
- **Search Functionality**: Search for employees by name or department.
- **SQLite Database**: Utilizes SQLite for a lightweight, file-based database solution.

## Technologies Used

- **JavaFX**: For building the user interface.
- **SQLite3**: For storing and managing employee data.
- **JDK 21**: The application is built and tested using JDK 21.
- **Maven**: Build automation tool for Java.

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: [Download JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- **SQLite3**: SQLite is included as a dependency in the project.
- **Maven**: [Download Maven](https://maven.apache.org/download.cgi)

## Project Structure

```
ems/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   ├── com
│       │   │   └── jonasgestopa
│       │   │       └── ems
│       │   └── module-info.java
│       └── resources
│           └── com
│               └── jonasgestopa
│                   └── ems
├── target
│   ├── EMS-1.0.dmg
│   ├── classes
│   │   ├── com
│   │   │   └── jonasgestopa
│   │   │       └── ems
│   │   │           ├── AdminLoginController.class
│   │   │           ├── Controllers
│   │   │           ├── MainApplication.class
│   │   │           ├── MainController.class
│   │   │           ├── Models
│   │   │           ├── Repositories
│   │   │           ├── Utils
│   │   │           ├── admin-login-view.fxml
│   │   │           ├── database
│   │   │           ├── main-view.fxml
│   │   │           └── switch-views
│   │   └── module-info.class
│   ├── generated-sources
│   │   └── annotations
│   ├── lib
│   │   ├── ems-1.0-SNAPSHOT.jar
│   │   ├── slf4j-api-2.0.16.jar
│   │   └── sqlite-jdbc-3.44.1.0.jar
│   ├── maven-archiver
│   │   └── pom.properties
│   └── maven-status
│       └── maven-compiler-plugin
│           └── compile
│               └── default-compile
│                   ├── createdFiles.lst
│                   └── inputFiles.lst
```

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-username/employment-management-system.git
   cd employment-management-system
   ```

2. **Open the project in IntelliJ IDEA or your preferred IDE**.

3. **Run the Application**:
   ```bash
   mvn javafx:run
   ```

## Creating Package

1. **Move to the employment-management-system folder**
   ```bash
  cd employment-management-system
  cd target
  mkdir lib
  cp 
   ```

## Usage

1. **Launch the application** from your IDE or using the generated JAR file.
2. **Manage Employees**:
   - Add a new employee by filling in the form.
   - Update employee details as required.
   - Search for employees using the search bar.
   - Delete an employee when no longer needed.
3. **Database**: All employee data is stored in `database.db`, which is automatically created in the `resources` folder.

## Database Schema

The SQLite database contains a single `employees` table with the following structure:

```sql
CREATE TABLE "employees" (
    employee_id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    gender TEXT NOT NULL,
    phone_number INTEGER,
    position TEXT NOT NULL CHECK(position IN ('Software Engineer', 'Project Manager', 'Data Analyst', 'UI/UX Designer', 'DevOps Engineer')),
    image TEXT,
    salary INTEGER,
    UNIQUE(first_name, last_name)
)
```


# Tags

#JavaFX #SQLite #EmploymentManagementSystem #CRUDApplication #JavaDesktopApp #SQLite3 #JDK21 #JavaFXApplication #HRSystem #EmployeeManagement #DatabaseApplication #JavaProject
