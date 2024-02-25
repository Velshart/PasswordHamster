module com.example.passwordhamster {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.passwordhamster to javafx.fxml;
    exports com.example.passwordhamster;
    exports com.example.passwordhamster.controllers;
    opens com.example.passwordhamster.controllers to javafx.fxml;
}