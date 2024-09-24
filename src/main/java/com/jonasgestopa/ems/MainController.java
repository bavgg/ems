package com.jonasgestopa.ems;

import com.jonasgestopa.ems.Models.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    @FXML
    VBox mainContainer;
    @FXML
    Button employeeButton;

    @FXML
    Button homeButton;

    @FXML
    Button salaryButton;

    @FXML
    private TableView<Employee> employeeTableView;

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

    @FXML
    public void initialize() {

        // Switch views
        handleSwitchViews("home-view");
        employeeButton.setOnAction(event -> handleSwitchViews("employee-view"));
        homeButton.setOnAction(event -> handleSwitchViews("home-view"));
        salaryButton.setOnAction(event -> handleSwitchViews("salary-view"));

    }

    private void handleSwitchViews(String view) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource(String.format("switch-views/%s.fxml", view)));
            Parent itemView = fxmlLoader.load();

            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(itemView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
