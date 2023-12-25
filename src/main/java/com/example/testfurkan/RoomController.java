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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
    private Button previousPageButton;

    @FXML
    private TableColumn<Guest, String> surname;
    @FXML
    private Button refreshButton;

    private int roomID;
    List<Guest> listGuest;

    public RoomController(int roomID){
        this.roomID= roomID;
        listGuest = bll.GetGuestListByRoom(roomID);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        folioPaymentLabel.setText(String.valueOf(bll.GetAllFolio(roomID).getBalance()));

        roomIDLabel.setText((String.valueOf(roomID)));
        int daysLeftCheck = 0;
        if(listGuest.size()!=0){
            daysLeftCheck = bll.ControlCheckOutDate(listGuest.get(0).getTC(), roomID);
            checkinDate.setText(listGuest.get(0).getCheckIn().toString());
            checkoutDate.setText(listGuest.get(0).getCheckOut().toString()); 
        }
        else{   
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
    public void checkOutButtonClick(){
        int checkValue = bll.ControlCheckOutDate(listGuest.get(0).getTC(), roomID);
        int stayedDays = bll.CheckDaysOfGuest(listGuest.get(0).getTC(), roomID);
        if(checkValue==0)//zamanında çıkış
        {
            System.out.println("Hesaplama yapıldı ödenecek miktar : ");
            System.out.println("Toplam kalınan gün "+ stayedDays);
        }
        if(checkValue<0)//zamanından sonra çıkış
        {
            System.out.println(Math.abs(checkValue)+" gün fazladan kaldı");
            System.out.println("Toplam kalınan gün "+ stayedDays);
        }
        if(checkValue>0)//zamanından önce çıkışs
        {
            System.out.println(checkValue+"gün eksik kaldı");
            System.out.println("Toplam kalınan gün "+ stayedDays);
        }
    }

    public void getFolioButton(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("folio_page.fxml"));
        FolioPageController controller = new FolioPageController(roomID);
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void addNewGuestButtonClick() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add_new_guest_page.fxml"));
        NewGuestPageController controller = new NewGuestPageController(roomID);
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void refreshButtonClick(){
        ObservableList<Guest> list = FXCollections.observableArrayList(bll.GetGuestListByRoom(roomID));
        id.setCellValueFactory(new PropertyValueFactory<>("TC"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        table.setItems(list);

        int daysLeftCheck = bll.ControlCheckOutDate(listGuest.get(0).getTC(), roomID);
         daysLeft.setText((String.valueOf(daysLeftCheck)));
        checkinDate.setText(listGuest.get(0).getCheckIn().toString());
        checkoutDate.setText(listGuest.get(0).getCheckOut().toString());

        folioPaymentLabel.setText(String.valueOf(bll.GetAllFolio(roomID).getBalance()));
    }

    public void previousPageButtonClick() throws IOException{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
            Stage stage = (Stage) previousPageButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800.0, 600.0));
    }

}
