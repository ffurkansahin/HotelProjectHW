package com.example.testfurkan;

import BusinessLayer.BLL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    static BLL bll = LoginController.bll;

    @FXML
    private Label room1;

    @FXML
    private Label room10;

    @FXML
    private Label room2;

    @FXML
    private Label room3;

    @FXML
    private Label room5;

    @FXML
    private Label room6;

    @FXML
    private Label room7;

    @FXML
    private Label room8;

    @FXML
    private Label room9;

    @FXML
    private Label room4;

    @FXML
    private Label username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void mouseClicked() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("room_detail.fxml")));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Something bad happened");
        }
    }
}
