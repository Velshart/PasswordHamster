module com.example.passwordhamster {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.passwordhamster to javafx.fxml, com.google.gson;
    opens com.example.passwordhamster.object to com.google.gson;
    exports com.example.passwordhamster;
    exports com.example.passwordhamster.controller;
    opens com.example.passwordhamster.controller to javafx.fxml;
    opens com.example.passwordhamster.json to com.google.gson;
    exports com.example.passwordhamster.enumeration;
    opens com.example.passwordhamster.enumeration to com.google.gson, javafx.fxml;
}