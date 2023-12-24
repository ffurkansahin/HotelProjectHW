package com.example.testfurkan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.HashMap;
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


    public FolioPageController(int roomID){
        this.roomID=roomID;
        folio = bll.GetAllFolio(roomID);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        totalCostLabel.setText(String.valueOf(folio.getBalance())+"$");
        fillListViewWithProducts();
        
    }

    public void payAllFolio()
    {
        totalCostLabel.setText(" ");
        productsListview.setItems(null);
    }
    public void payFolio(){
        double totalBalance = folio.getBalance();
         
        double newCost = totalBalance - Double.parseDouble(folioPayText.getText());
        totalCostLabel.setText(String.valueOf(newCost)+"$");
    }
    public void addProductToFolio()
    {
        HashMap<String,Double> hashMap = folio.getProducts();
        String addProductName = productNameField.getText();
        Double addProductValue = (Double.parseDouble(priceField.getText())) * productCountSpinner.getValue();
        hashMap.put(addProductName, addProductValue);
        fillListViewWithProducts();
    }
    public void fillListViewWithProducts()
    {
        HashMap<String,Double> hashMap= folio.getProducts();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (String key : hashMap.keySet()) {
            Double value = hashMap.get(key);
            String row = key + " ------------------> " + value.toString();
            list.add(row);
        }
        productsListview.setItems(list);
    }

}
