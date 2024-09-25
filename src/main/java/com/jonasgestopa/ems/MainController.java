package com.jonasgestopa.ems;

import com.jonasgestopa.ems.Models.Employee;
import javafx.fxml.FXML;

import javafx.scene.Node;

import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private Node salaryNode;
    @FXML
    private Node employeeNode;
    @FXML
    private Node homeNode;

    @FXML
    private AnchorPane homeView;
    @FXML
    private VBox employeeView;
    @FXML
    private HBox salaryView;

    @FXML
    public void initialize() {

        homeView = (AnchorPane) ((AnchorPane) homeNode).lookup("#homeView");
        employeeView = (VBox) ((VBox) employeeNode).lookup("#employeeView");
        salaryView = (HBox) ((HBox) salaryNode).lookup("#salaryView");

        homeView.setVisible(false);
        homeView.setManaged(false);
        employeeView.setVisible(true);
        employeeView.setManaged(true);
        salaryView.setVisible(false);
        salaryView.setManaged(false);

        employeeButton.setOnAction(event -> setMain("employee-view"));
        homeButton.setOnAction(event -> setMain("home-view"));
        salaryButton.setOnAction(event -> setMain("salary-view"));
    }

    private void setMain(String view) {
        if (view == "employee-view") {
            homeView.setVisible(false);
            homeView.setManaged(false);
            salaryView.setVisible(false);
            salaryView.setManaged(false);
            employeeView.setVisible(true);
            employeeView.setManaged(true);
        } else if (view == "home-view") {
            homeView.setVisible(true);
            homeView.setManaged(true);
            salaryView.setVisible(false);
            salaryView.setManaged(false);
            employeeView.setVisible(false);
            employeeView.setManaged(false);
        } else if (view == "salary-view") {
            homeView.setVisible(false);
            homeView.setManaged(false);
            salaryView.setVisible(true);
            salaryView.setManaged(true);
            employeeView.setVisible(false);
            employeeView.setManaged(false);
        }

    }

}
