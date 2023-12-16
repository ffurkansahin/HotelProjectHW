package com.example.testfurkan;

import BusinessLayer.BLL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    BLL bll = new BLL();
    @FXML
    public Button backButton = new Button();

    @FXML
    private PasswordField passwordField = new PasswordField();

    @FXML
    private Button registerButton = new Button();

    @FXML
    private Button signInButton = new Button();

    @FXML
    private TextField usernameField = new TextField();
    @FXML
    private TextField nameForRegister = new TextField();

    @FXML
    private PasswordField passwordForRegister = new PasswordField();

    @FXML
    private TextField surnameForRegister = new TextField();

    @FXML
    private TextField usernameForRegister = new TextField();
    @FXML
    private Button registerPageButton = new Button();

    public void loginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        int checker = bll.CheckUserForLogin(username, password);
        if (checker == 1) {
            System.out.println("User Logged In");
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public void registerAction() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root, 205, 225));
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (bll.CheckUserForLogin(username, password) == -1) {
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}