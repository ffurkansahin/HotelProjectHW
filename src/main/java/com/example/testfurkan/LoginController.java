package com.example.testfurkan;

import BusinessLayer.BLL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    BLL bll = Main.bll;


    @FXML
    private Button loginButton = new Button();

    @FXML
    private PasswordField passwdLabel = new PasswordField();

    @FXML
    private TextField usernameLabel = new TextField();

    public void login() throws IOException {
        String username = usernameLabel.getText();
        String passwd = passwdLabel.getText();
        int result = bll.CheckUserForLogin(username, passwd);
        if (result == 1) {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("dashboard.css")).toExternalForm());
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid acount!");
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }

}
