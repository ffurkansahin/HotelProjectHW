package com.example.testfurkan;

import BusinessLayer.BLL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController {

    static BLL bll = new BLL();


    static {
        bll.AddUser("niso", "ahmedo", "ahmed", "blal");
    }

    @FXML
    public Button loginButton = new Button();

    @FXML
    private PasswordField passwdLabel = new PasswordField();

    @FXML
    private TextField usernameLabel = new TextField();

    public void login() {
        String username = usernameLabel.getText();
        String passwd = passwdLabel.getText();
        int result = bll.CheckUserForLogin(username, passwd);
        if (result == 1) {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user_dashboard.fxml")));
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                System.out.println("Something bad happened");
            }
        }
    }


    public PasswordField getPasswdLabel() {
        return passwdLabel;
    }

    public TextField getUsernameLabel() {
        return usernameLabel;
    }
}

