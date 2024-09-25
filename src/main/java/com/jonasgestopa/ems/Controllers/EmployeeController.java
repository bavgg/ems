package com.jonasgestopa.ems.Controllers;

import com.jonasgestopa.ems.Models.Employee;
import com.jonasgestopa.ems.Repositories.EmployeeRepository;
import com.jonasgestopa.ems.Repositories.EmployeeRepositoryImpl;
import com.jonasgestopa.ems.Utils.Database;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    @FXML
    VBox mainContainer;

    @FXML
    Button addButton;
    @FXML
    Button updateButton;
    @FXML
    Button deleteButton;
    @FXML
    Button clearButton;

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

    private Integer employeeID;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ToggleGroup genderToggleGroup;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private ComboBox<String> positionComboBox;

    @FXML
    RadioButton maleRadioButton;
    @FXML
    RadioButton femaleRadioButton;
    @FXML
    RadioButton otherRadioButton;

    @FXML
    private TextField searchField;

    private Connection connection;

    private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

    @FXML
    public void initialize() {
        genderToggleGroup = new ToggleGroup();
        otherRadioButton.setToggleGroup(genderToggleGroup);
        maleRadioButton.setToggleGroup(genderToggleGroup);
        femaleRadioButton.setToggleGroup(genderToggleGroup);

        positionComboBox.getItems().addAll("Software Engineer", "Project Manager", "Data Analyst", "UI/UX Designer", "DevOps Engineer");

        // Initialize columns and set up button actions
        setupTableColumns();
        loadEmployeeData();

        addButton.setOnAction(event -> handleAddButton());
        updateButton.setOnAction(event -> handleUpdateButton());
        deleteButton.setOnAction(event -> handleDeleteButton());
        clearButton.setOnAction(event -> handleClearButton());

        employeesTable.setOnMouseClicked(event -> handleEmployeesTable());
        
        
    }

    public String getSelectedGender() {
        // Get the selected RadioButton from the ToggleGroup
        RadioButton selectedRadioButton = (RadioButton) genderToggleGroup.getSelectedToggle();

        // Return the text of the selected RadioButton (Male, Female, or Other)
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        }
        return null; // No selection
    }

    private void handleEmployeesTable() {
        Employee emp = employeesTable.getSelectionModel().getSelectedItem();
        // System.out.println(emp.toString());
        String firstName = emp.getFirstName();
        String lastName = emp.getLastName();
        String gender = emp.getGender();
        Integer phoneNumber = emp.getPhoneNum();
        String position = emp.getPosition();
        employeeID = emp.getEmployeeId();

        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);

        System.out.println(gender);

        if (gender.equals("Male")) {

            maleRadioButton.setSelected(true);
            femaleRadioButton.setSelected(false);
            otherRadioButton.setSelected(false);
        } else if (gender.equals("Female")) {

            femaleRadioButton.setSelected(true);
            maleRadioButton.setSelected(false);
            otherRadioButton.setSelected(false);
        } else {

            otherRadioButton.setSelected(true);
            femaleRadioButton.setSelected(false);
            maleRadioButton.setSelected(false);
        }

        phoneNumberTextField.setText(String.valueOf(phoneNumber));
        positionComboBox.setValue(position);

    }

    private void setupTableColumns() {
        // Set cell value factories for the columns
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
    }

    private void loadEmployeeData() {
        List<Employee> employees = employeeRepository.getAllEmployees();

        for (Employee employee : employees) {
            System.out.println(employee);
        }
        employeesTable.getItems().setAll(employees);
    }

    private void handleAddButton() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String gender = getSelectedGender();
        Integer phoneNumber = Integer.valueOf(phoneNumberTextField.getText());
        String position = positionComboBox.getValue();

        Employee emp = new Employee(firstName, lastName, gender, phoneNumber, position);
        employeeRepository.addEmployee(emp);

        loadEmployeeData();
    }

    private void handleUpdateButton() {
        System.out.println("heheelsososoosos");
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String gender = getSelectedGender();
        System.out.println("This is gender section");
        System.out.println(gender);
        Integer phoneNumber = Integer.valueOf(phoneNumberTextField.getText());
        String position = positionComboBox.getValue();
        Integer empID = employeeID;

        Employee emp = new Employee(empID, firstName, lastName, gender, phoneNumber, position);
        
        System.out.println(firstName);
        employeeRepository.updateEmployee(emp);

        loadEmployeeData();
    }

    private void handleDeleteButton() {
        employeeRepository.deleteEmployee(employeeID);
        loadEmployeeData();
    }

    private void handleClearButton() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        otherRadioButton.setSelected(false);
        phoneNumberTextField.setText("");
        positionComboBox.setValue(null);
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
