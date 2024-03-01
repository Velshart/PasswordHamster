package com.example.passwordhamster.controller;

import com.example.passwordhamster.object.Login;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChangePasswordController {
    private final String PASSWORDS_DIFFERENT_OR_EMPTY = "THE PASSWORDS GIVEN ARE DIFFERENT OR SOME FIELDS ARE EMPTY";
    private final String PASSWORD_CHANGED = "THE PASSWORD HAS BEEN CHANGED SUCCESSFULLY";

    @FXML
    private TextField changePasswordOldPasswordTextField;

    @FXML
    private TextField changePasswordNewPasswordField;

    @FXML
    private TextField changePasswordErrorTextField;

    @FXML
    private Button changePasswordChangeButton;

    @FXML
    public void onChangePasswordChangeButtonClick() {
        String oldPassword = changePasswordOldPasswordTextField.getText();
        String newPassword = changePasswordNewPasswordField.getText();

            if(!oldPassword.isEmpty() && !newPassword.isEmpty()) {
                writePasswordToLoginFile(newPassword);


                changePasswordOldPasswordTextField.setText("");
                changePasswordNewPasswordField.setText("");

                changePasswordErrorTextField.setText(PASSWORD_CHANGED);
            }else {
                changePasswordErrorTextField.setText(PASSWORDS_DIFFERENT_OR_EMPTY);
            }
    }

    private void writePasswordToLoginFile(String newPassword) {
        String filename = "login.json";

        try (FileReader fileReader = new FileReader(filename)) {
            Gson gson = new Gson();
            Login userPassword = gson.fromJson(fileReader, Login.class);

            userPassword.setPassword(newPassword);
            Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
            String jsonData = gsonBuilder.toJson(userPassword);

            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(jsonData);
            fileWriter.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
