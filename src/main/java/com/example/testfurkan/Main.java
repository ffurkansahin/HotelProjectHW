package com.example.testfurkan;

import BusinessLayer.BLL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Objects;

public class Main extends Application {
    public static BLL bll = new BLL();
    static {
        
    }
    public static void main(String[] args){
        bll.AddUser("admin","admin","admin", "admin");
        for (int i = 1; i <= 10; i++) {
            bll.CreateRoom(i);

            /* bll.AddGuestToRoom("11111111111", "test", "test", LocalDate.of(2023, 12, 21), LocalDate.of(2023, 12, 30), i);
            bll.AddProductToFolio(i, "Pizza", 50);
            bll.AddProductToFolio(i, "Coca-Cola", 20); */
        }
        launch();
    }
    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login_page.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);


        stage.setTitle("Hotel Management");

        stage.show();
    }


}