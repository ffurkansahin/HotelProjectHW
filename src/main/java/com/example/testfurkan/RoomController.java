package com.example.testfurkan;

import BusinessLayer.BLL;
import Entities.Guest;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomController implements Initializable{
    BLL bll = Main.bll;
    @FXML
    private TableView<Guest> table = new TableView<>();
    @FXML
    private Button addNewGuestButton;

    @FXML
    private Button checkOutButton;

    @FXML
    private Label checkinDate;

    @FXML
    private Label checkoutDate;

    @FXML
    private Label daysLeft;

    @FXML
    private Label folioPaymentLabel;

    @FXML
    private TableColumn<Guest, String> id;

    @FXML
    private TableColumn<Guest, String> name;

    @FXML
    private Button openFolioButton;

    @FXML
    private Label roomIDLabel;

    @FXML
    private TableColumn<Guest, String> surname;

    private int roomID;

    public RoomController(int roomID){
        this.roomID= roomID;
    }

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Guest> list = FXCollections.observableArrayList(bll.GetGuestListByRoom(roomID));
        id.setCellValueFactory(new PropertyValueFactory<>("TC"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        table.setItems(list);
    }


}
