package com.example.testfurkan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import BusinessLayer.BLL;
import Entities.Folio;

public class FolioPageController implements Initializable {
    BLL bll = Main.bll;
    @FXML
    public ListView<String> productsListview;
    @FXML
    public Label totalCostLabel;
    @FXML
    public Button payAllButton;
    @FXML
    public Button addProductToFolioButton;
    @FXML
    public TextField productNameField;
    @FXML
    public TextField priceField;
    @FXML
    public Spinner<Integer> productCountSpinner;
    @FXML
    public Label costLabel;
    @FXML
    public Button payButton;
    @FXML
    public TextField folioPayText;

    public Folio folio;

    public int roomID;

    ObservableList<String> list;

    public boolean isInitialized;


    public FolioPageController(int roomID){
        this.roomID=roomID;
        folio = bll.GetAllFolio(roomID);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            list = FXCollections.observableArrayList();
            if(!folio.getProducts().containsKey("Hotel Price")){
                double price = (bll.CheckDaysOfGuest(bll.GetGuestListByRoom(roomID).get(0).getTC(), roomID)) * 100;
                bll.AddProductToFolio(roomID, "Hotel Price", price);
            }
            fillListViewWithProducts();
    }

    public void payAllFolio()
    {
        folio.getProducts().clear();
        folio.setBalance(0);
        totalCostLabel.setText("0");
        productsListview.setItems(null);
        list.clear();
    }
    public void payFolio(){
        double totalBalance = folio.getBalance();
        folio.setBalance(totalBalance-Double.parseDouble(folioPayText.getText()));
        double newCost = totalBalance - Double.parseDouble(folioPayText.getText());
        totalCostLabel.setText(String.valueOf(newCost)+"$");
        list.add("Ödenen miktar ----------->"+folioPayText.getText());
    }
    public void addProductToFolio()
    {
        String addProductName = productNameField.getText();
        Double addProductValue = (Double.parseDouble(priceField.getText())) * productCountSpinner.getValue();
        bll.AddProductToFolio(roomID, addProductName, addProductValue);
        fillListViewWithProducts();
        
    }
    public void fillListViewWithProducts()
    {
        list.clear();
        HashMap<String,Double> hashMap= folio.getProducts(); 
        for (String key : hashMap.keySet()) {
            Double value = hashMap.get(key);
            String row = key + " ------------------> " + value.toString();
            list.add(row);
        }
        productsListview.setItems(list);
        double balance = folio.getBalance();
        totalCostLabel.setText(String.valueOf(balance)+"$");
    }
}
