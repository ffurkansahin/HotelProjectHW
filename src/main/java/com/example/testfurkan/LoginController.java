package com.example.testfurkan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button RegisterButton;

    @FXML
    private Button SignInButton;

    @FXML
    private TextField UsernameField;

    public void loginAction(){
        System.out.println("Hello World!!!");
    }
}
