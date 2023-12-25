package com.example.testfurkan;

import BusinessLayer.BLL;
import Entities.Room;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private GridPane gridpane;
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
        // Sağ tık menüsü oluştur
        ContextMenu contextMenu = new ContextMenu();
        MenuItem cleanItem = new MenuItem("Clean Room");

        // MenuItem'lari ContextMenu'ya ekle
        contextMenu.getItems().addAll(cleanItem);

        cleanItem.setOnAction(e -> {
            // "Cut" seçeneği için yapılacak işlemleri buraya ekleyin
            Button button = (Button) e.getSource();
            int bId = Integer.parseInt(button.getText());
            List<Room> rooms = bll.GetAllRooms();
            rooms.get(bId).setClean(true);
            System.out.println(rooms.get(bId).isClean());
        });

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

        ObservableList<Node> myList = gridpane.getChildren();

        List<Room> rooms = bll.GetAllRooms();

        for (int i = 0; i<10 ; i++){
            Button button = (Button) myList.get(i);
            button.getStyleClass().clear();
            button.setContextMenu(contextMenu);
            if (!rooms.get(i).isEmpty()){
                button.getStyleClass().add("doluRoom");
            } else if (rooms.get(i).isClean()) {
                button.getStyleClass().add("activeRoom");
            }else  {
                button.getStyleClass().add("nonActiveRoom");
            }
        }

    }
    public void signoutButtonClick() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login_page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) signoutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
