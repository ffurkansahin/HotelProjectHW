package com.example.testfurkan;

import BusinessLayer.BLL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NewGuestPageController implements Initializable {
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
        bll.GetAllRooms().get(roomId-1).setEmpty(false);
        bll.GetAllRooms().get(roomId-1).setClean(false);
        Stage stage = (Stage) addNewGuestButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add a listener to the value property of the DatePicker
        checkoutDatePicker.setDisable(true);

        checkinDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                LocalDate today = LocalDate.now();

                setDisable(date.isAfter(today));
            }
        });

        checkinDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                checkoutDatePicker.setDisable(false);
                checkoutDatePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);

                        setDisable(date.isBefore(newValue));
                    }
                });
            }
        });
    }
}