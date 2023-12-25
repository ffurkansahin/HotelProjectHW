package com.example.testfurkan;

import BusinessLayer.BLL;
import Entities.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    BLL bll = Main.bll;
    
    @FXML
    private Label availableLabel;

    @FXML
    private Label dateNowLabel;

    @FXML
    private Label percentageLabel;

    @FXML 
    private Button signoutButton;


    public void clickEvent(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
        RoomController controller = new RoomController(Integer.parseInt(button.getText()));
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateNowLabel.setText(LocalDate.now().toString());
        int counter =0;
        for (Room room : bll.GetAllRooms()) {
            if(!room.getGuests().isEmpty()){
                counter++;
            }
        }
        percentageLabel.setText("percentage:%"+ counter*10);
        int availableRoom = 0;
        for (Room room : bll.GetAllRooms()) {
            if(room.isEmpty()==true){
                availableRoom++;
            }
        }
        availableLabel.setText("Available Room : "+(String.valueOf(availableRoom)));

    }
    public void signoutButtonClick() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login_page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) signoutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
