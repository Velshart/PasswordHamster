package com.example.passwordhamster.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SavedPasswordsController {
    @FXML
    private Button deletePasswordButton;

    @FXML
    private TextField passwordToDeleteTextField;

    @FXML
    private TextArea savedPasswordsTextArea;

    @FXML
    private TextField savedPasswordsErrorTextField;

    @FXML
    public void onSavedPasswordsDeleteButtonClick() {

    }
}
