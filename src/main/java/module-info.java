module com.example.testfurkan {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testfurkan to javafx.fxml;
    opens Entities to javafx.fxml;
    exports com.example.testfurkan;
    exports Entities;
}