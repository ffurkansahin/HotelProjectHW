package com.example.testfurkan;

import BusinessLayer.BLL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController  implements Initializable {
    static BLL bll = LoginController.bll;

    static {
        for (int i = 1; i <= 10; i++) {
            bll.CreateRoom(i);
            bll.AddGuestToRoom("11111111111", "test", "test", LocalDate.now(), LocalDate.of(2023, 12, 30), i);
        }
    }

    @FXML
    private GridPane gripPane;

    @FXML
    private Label room1;

    @FXML
    private Label room2;

    @FXML
    private Label room3;

    @FXML
    private Label room4;

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
    private Label room10;

    @FXML
    private Label guestoR1;

//    public void addGuestToLabel() {
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guestoR1.setText(bll.GetAllRooms().get(0).getGuests().get(0).getName());

    }
}
