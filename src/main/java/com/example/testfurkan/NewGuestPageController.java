package com.example.testfurkan;

import BusinessLayer.BLL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewGuestPageController {
    BLL bll = Main.bll;
    @FXML
    private Button addNewGuestButton;

    @FXML
    private DatePicker checkinDatePicker;

    @FXML
    private DatePicker checkoutDatePicker;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField tcField;


    private int roomId;

    public NewGuestPageController(int roomId)
    {
        this.roomId=roomId;
    }

    public void addNewGuestButtonClick(){
        bll.AddGuestToRoom(tcField.getText(),nameField.getText(),surnameField.getText(),checkinDatePicker.getValue(),checkoutDatePicker.getValue(),roomId);
        Stage stage = (Stage) addNewGuestButton.getScene().getWindow();
        stage.close();
    }
    
}