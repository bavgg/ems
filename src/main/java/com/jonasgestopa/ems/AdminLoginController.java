package com.jonasgestopa.ems;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        // Logic for loginButton can go here
        loginButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Implement your authentication logic
        if (username.equals("admin") && password.equals("password123")) {
            System.out.println("Login Successful!");
            loadDashboard();
        } else {
            System.out.println("Login Failed!");
        }
    }

    private void loadDashboard() {
        try {
            loginButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Scene dashboardScene = new Scene(loader.load());
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(dashboardScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
