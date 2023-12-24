package com.example.testfurkan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class FolioPageController implements Initializable {
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
    public TextField folioPayButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
