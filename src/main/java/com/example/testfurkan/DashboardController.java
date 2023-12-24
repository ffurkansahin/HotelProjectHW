package com.example.testfurkan;

import BusinessLayer.BLL;
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

public class DashboardController {
    BLL bll = Main.bll;


    public void clickEvent(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
        RoomController controller = new RoomController(Integer.parseInt(button.getText()));
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
