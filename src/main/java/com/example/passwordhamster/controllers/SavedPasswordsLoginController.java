package com.example.passwordhamster.controllers;

import com.example.passwordhamster.PasswordHamster;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SavedPasswordsLoginController {
    @FXML
    private Button savedPasswordsLoginOkButton;

    @FXML
    public void onSavedPasswordsLoginOkButtonClick() {

        //correct password has been provided

        Stage savedPasswordsStage = new Stage();
        savedPasswordsStage.setResizable(false);
        savedPasswordsStage.setTitle("Saved passwords");

        FXMLLoader savedPasswordsLoader = new FXMLLoader(PasswordHamster.class.getResource("saved-passwords-view.fxml"));

        try {
            Scene savedPasswordsScene = new Scene(savedPasswordsLoader.load(), 666, 442);
            savedPasswordsStage.setScene(savedPasswordsScene);

            savedPasswordsStage.show();

            ((Stage)savedPasswordsLoginOkButton.getScene().getWindow()).close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
