package com.example.passwordhamster.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SavePasswordController {
    @FXML
    private Button savePasswordOkButton;

    @FXML
    public void onSavePasswordOkButtonClick() {
        //saving

        ((Stage)savePasswordOkButton.getScene().getWindow()).close();
    }
}
