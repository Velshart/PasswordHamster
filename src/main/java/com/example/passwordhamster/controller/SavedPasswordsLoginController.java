package com.example.passwordhamster.controller;

import com.example.passwordhamster.PasswordHamster;
import com.example.passwordhamster.json.JsonReader;
import com.example.passwordhamster.object.Login;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;

public class SavedPasswordsLoginController {
    @FXML
    private Button savedPasswordsLoginOkButton;

    @FXML
    private TextField savedPasswordsLoginPasswordField;

    @FXML
    public void onSavedPasswordsLoginOkButtonClick() {
        String providedPassword = savedPasswordsLoginPasswordField.getText();

        if(readUserPassword().equals(providedPassword)) {
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
                throw new RuntimeException(ex);
            }
        }
    }

    private String readUserPassword() {
        String password;
        String filename = "login.json";
        try (FileReader fileReader = new FileReader(filename)) {
            Gson gson = new Gson();
            Login userPassword = gson.fromJson(fileReader, Login.class);

            password = userPassword.getPassword();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return password;
    }
}
