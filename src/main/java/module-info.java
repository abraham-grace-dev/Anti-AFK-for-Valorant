module com.example.antiafk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.antiafk to javafx.fxml;
    exports com.example.antiafk;
}