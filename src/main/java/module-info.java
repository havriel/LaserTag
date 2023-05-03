module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires arduino;
    requires com.fazecast.jSerialComm;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    requires mysql.connector.j;
    exports com.example.demo;
}