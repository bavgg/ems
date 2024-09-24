package com.jonasgestopa.ems.Controllers;

import com.jonasgestopa.ems.Models.Employee;
import com.jonasgestopa.ems.Repositories.EmployeeRepository;
import com.jonasgestopa.ems.Repositories.EmployeeRepositoryImpl;
import com.jonasgestopa.ems.Utils.Database;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    @FXML
    VBox mainContainer;

    @FXML
    Button addEmployeeButton;

    @FXML
    Button updateEmployeeButton;

    @FXML
    Button deleteEmployeeButton;

    @FXML
    Button searchButton;

    @FXML
    private TableView<Employee> employeesTable;

    @FXML
    private TableColumn<Employee, Integer> employeeIdColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> genderColumn;
    @FXML
    private TableColumn<Employee, String> phoneNumColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;
    @FXML
    private TableColumn<Employee, String> imageColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    @FXML
    private TextField searchField;

    private Connection connection;

    private EmployeeRepository employeeRepository= new EmployeeRepositoryImpl();

    @FXML
    public void initialize() {
        // Initialize columns and set up button actions
        setupTableColumns();
        loadEmployeeData();

        addEmployeeButton.setOnAction(event -> showAddEmployeeDialog());
        updateEmployeeButton.setOnAction(event -> showUpdateEmployeeDialog());
        deleteEmployeeButton.setOnAction(event -> deleteSelectedEmployee());
        searchButton.setOnAction(event -> searchEmployee());
    }

    private void setupTableColumns() {
        // Set cell value factories for the columns
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void loadEmployeeData() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        employeesTable.getItems().setAll(employees);
    }

    private void showAddEmployeeDialog() {
        // Implement logic to show add employee dialog
        // For example, open a new window with a form
    }

    private void showUpdateEmployeeDialog() {
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            // Implement logic to show update employee dialog with selectedEmployee's data
        } else {
            showAlert("Warning", "Please select an employee to update.");
        }
    }

    private void deleteSelectedEmployee() {
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            String sql = "DELETE FROM employees WHERE employee_id = ?";
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:your_database_name.db");
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, selectedEmployee.getEmployeeId());
                pstmt.executeUpdate();
                employeesTable.getItems().remove(selectedEmployee);
            } catch (SQLException e) {
                showAlert("Error", "Failed to delete employee: " + e.getMessage());
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    showAlert("Error", "Failed to close the database connection: " + e.getMessage());
                }
            }
        } else {
            showAlert("Warning", "Please select an employee to delete.");
        }
    }

    private void searchEmployee() {
        String query = searchField.getText().toLowerCase();
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employeesTable.getItems()) {
            if (employee.getFirstName().toLowerCase().contains(query) ||
                    employee.getLastName().toLowerCase().contains(query)) {
                filteredEmployees.add(employee);
            }
        }
        employeesTable.getItems().setAll(filteredEmployees);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
