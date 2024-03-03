package com.example.passwordhamster.controller;

import com.example.passwordhamster.json.PasswordSaver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PasswordChangeRequiredController {

    @FXML
    private Button passwordChangeRequiredOkButton;

    @FXML
    public void onPasswordChangeRequiredOkButtonClick() {
            ((Stage)passwordChangeRequiredOkButton.getScene().getWindow()).close();
    }
}
