package com.example.passwordhamster.controller;

import com.example.passwordhamster.object.Password;
import com.example.passwordhamster.object.PasswordSaver;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class SavedPasswordsController {
    @FXML
    private TextField passwordToDeleteTextField;

    @FXML
    private TextArea savedPasswordsTextArea;


    @FXML
    public void initialize() {
        List<Password> savedPasswords = PasswordSaver.loadPasswords();
        updatePasswordsList(savedPasswords);
    }

    @FXML
    public void onSavedPasswordsDeleteButtonClick() {
        String passwordToDelete = passwordToDeleteTextField.getText();
        if(!passwordToDelete.isEmpty()) {

            List<Password> passwords = PasswordSaver.loadPasswords();

            List<Password> updatedPasswords = passwords.stream().filter(password ->
                    !password.getPassword().equals(passwordToDelete)).collect(Collectors.toList());

            PasswordSaver.savePasswords(updatedPasswords);

            updatePasswordsList(updatedPasswords);

            passwordToDeleteTextField.clear();
        }
    }

    private void updatePasswordsList(List<Password> passwords) {
        StringBuilder passwordsListBuilder = new StringBuilder();
        for (Password savedPassword : passwords) {
            passwordsListBuilder.append("[")
                    .append(savedPassword.getTag())
                    .append("]")
                    .append(savedPassword.getPassword())
                    .append("\n");
        }
        savedPasswordsTextArea.setText(passwordsListBuilder.toString());
    }
}
