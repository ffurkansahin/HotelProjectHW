package com.example.testfurkan;

import BusinessLayer.BLL;
import Entities.Guest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class RoomController implements Initializable {
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
    private Button previousPageButton;

    @FXML
    private TableColumn<Guest, String> surname;
    @FXML
    private Button refreshButton;

    private int roomID;
    List<Guest> listGuest;

    public RoomController(int roomID) {
        this.roomID = roomID;
        listGuest = bll.GetGuestListByRoom(roomID);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        folioPaymentLabel.setText(String.valueOf(bll.GetAllFolio(roomID).getBalance()));

        roomIDLabel.setText((String.valueOf(roomID)));
        int daysLeftCheck = 0;
        if (listGuest.size() != 0) {
            daysLeftCheck = bll.ControlCheckOutDate(listGuest.get(0).getTC(), roomID);
            checkinDate.setText(listGuest.get(0).getCheckIn().toString());
            checkoutDate.setText(listGuest.get(0).getCheckOut().toString());
        } else {
            checkinDate.setText(LocalDate.now().toString());
            checkoutDate.setText(LocalDate.now().toString());
        }
        daysLeft.setText((String.valueOf(daysLeftCheck)));

        ObservableList<Guest> list = FXCollections.observableArrayList(bll.GetGuestListByRoom(roomID));
        id.setCellValueFactory(new PropertyValueFactory<>("TC"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        table.setItems(list);
    }

    public void checkOutButtonClick() {
        Alert checkoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        if (!bll.GetRoomByID(roomID).getGuests().isEmpty()) {
            checkoutAlert.setHeaderText("Checkout Confirmation");
            checkoutAlert.setResizable(true);

            double checkValue = bll.ControlCheckOutDate(listGuest.get(0).getTC(), roomID);

            if (checkValue == 0) {
                checkoutAlert.setContentText("Çıkışı onaylıyor musunuz?\nGünce Folio = " + bll.GetRoomByID(roomID).getFolios().getBalance());
                checker(checkoutAlert);
            }
            if (checkValue < 0) {
                checkoutAlert.setContentText(Math.abs(checkValue) + " gün fazladan kalındı " + Math.abs(checkValue) * 100 + "$ ekstra ödeme yapıldı mı?");
                checker(checkoutAlert);
            }
            if (checkValue > 0) {
                checkoutAlert.setContentText(checkValue + " gün eksik kaldı\nGünce Folio = " + bll.GetRoomByID(roomID).getFolios().getBalance());
                checker(checkoutAlert);
            }
            refreshButtonClick();
        } else {
            checkoutAlert.setContentText("Room is already empty!");
            checkoutAlert.showAndWait();
        }
    }

    private void checker(Alert checkoutAlert) {
        Optional<ButtonType> result = checkoutAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            bll.GetRoomByID(roomID).getGuests().clear();
            bll.GetRoomByID(roomID).setEmpty(true);
            bll.GetRoomByID(roomID).setClean(false);
            bll.GetRoomByID(roomID).getFolios().getProducts().clear();
            bll.GetRoomByID(roomID).getFolios().setBalance(0);
        }
    }

    public void getFolioButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("folio_page.fxml"));
        FolioPageController controller = new FolioPageController(roomID);
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addNewGuestButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add_new_guest_page.fxml"));
        NewGuestPageController controller = new NewGuestPageController(roomID);
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void refreshButtonClick() {
        try {
            ObservableList<Guest> list = FXCollections.observableArrayList(bll.GetGuestListByRoom(roomID));
            id.setCellValueFactory(new PropertyValueFactory<>("TC"));
            name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            table.setItems(list);
            if (!bll.GetGuestListByRoom(roomID).isEmpty()) {
                int daysLeftCheck = bll.ControlCheckOutDate(listGuest.get(0).getTC(), roomID);
                daysLeft.setText((String.valueOf(daysLeftCheck)));
                checkinDate.setText(listGuest.get(0).getCheckIn().toString());
                checkoutDate.setText(listGuest.get(0).getCheckOut().toString());
                folioPaymentLabel.setText(String.valueOf(bll.GetAllFolio(roomID).getBalance()));
            }
        } catch (Exception e) {
            System.out.println("Something bad happened");
        }
    }

    public void previousPageButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
        Stage stage = (Stage) previousPageButton.getScene().getWindow();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("dashboard.css")).toExternalForm());
        stage.setScene(scene);

    }

}
