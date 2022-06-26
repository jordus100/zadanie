module com.example.qbs_zadanie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.qbs_zadanie to javafx.fxml;
    exports com.example.qbs_zadanie;
}