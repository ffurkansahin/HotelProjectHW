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
    public TextField productNameField;
    @FXML
    public TextField priceField;
    @FXML
    public Spinner<Integer> productCountSpinner;
    @FXML
    public TextField folioPayText;

    public Folio folio;

    public int roomID;

    ObservableList<String> list;

    public FolioPageController(int roomID) {
        this.roomID = roomID;
        folio = bll.GetAllFolio(roomID);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        fillListViewWithProducts();
    }

    public void payAllFolio() {
        folio.getProducts().clear();
        folio.setBalance(0);
        totalCostLabel.setText("0");
        productsListview.setItems(null);
        list.clear();
    }

    public void payFolio() {
        double totalBalance = folio.getBalance();
        double price = -1 * Double.parseDouble(folioPayText.getText());
        bll.AddProductToFolio(roomID, "Payment", price);
        fillListViewWithProducts();
    }

    public void addProductToFolio() {
        String addProductName = productNameField.getText();
        double addProductValue = (Double.parseDouble(priceField.getText())) * productCountSpinner.getValue();
        bll.AddProductToFolio(roomID, addProductName, addProductValue);
        fillListViewWithProducts();
    }

    public void fillListViewWithProducts() {
        list.clear();
        HashMap<String, Double> hashMap = folio.getProducts();
        for (String key : hashMap.keySet()) {
            Double value = hashMap.get(key);
            String row = key + " ------------------> " + value.toString();
            list.add(row);
        }
        productsListview.setItems(list);
        double balance = folio.getBalance();
        totalCostLabel.setText(String.valueOf(balance) + "$");
    }
}
