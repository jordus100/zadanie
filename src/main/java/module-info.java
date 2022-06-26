module com.example.qbs_zadanie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.io;
    requires org.apache.commons.lang3;


    opens com.example.qbs_zadanie to javafx.fxml;
    exports com.example.qbs_zadanie;
}