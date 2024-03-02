package com.example.passwordhamster.controller;

import com.example.passwordhamster.enumeration.Errors;
import com.example.passwordhamster.json.PasswordSaver;
import com.example.passwordhamster.object.Password;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SavedPasswordsController {
    @FXML
    private TextField passwordToDeleteTextField;

    @FXML
    private TextArea savedPasswordsTextArea;

    @FXML
    private TextField savedPasswordsErrorTextField;

    @FXML
    private TextField savedPasswordsCustomPasswordPasswordTextField;

    @FXML
    private TextField savedPasswordsCustomPasswordTagTextField;

    @FXML
    private Button savedPasswordsAddCustomPasswordButton;


    @FXML
    public void initialize() {
        List<Password> savedPasswords = PasswordSaver.loadPasswords();
        updatePasswordsList(savedPasswords);
        savedPasswordsErrorTextField.setStyle("-fx-text-inner-color: red;");
    }

    @FXML
    public void onSavedPasswordsAddCustomPasswordButtonClick() {
        String customPassword = savedPasswordsCustomPasswordPasswordTextField.getText();
        String customPasswordTag = savedPasswordsCustomPasswordTagTextField.getText();

        if(!customPassword.isEmpty() && !customPasswordTag.isEmpty()) {
            List<Password> savedPasswords = PasswordSaver.loadPasswords();
            savedPasswords.add(new Password(customPasswordTag, customPassword));

            PasswordSaver.savePasswords(savedPasswords);
            updatePasswordsList(savedPasswords);

            savedPasswordsCustomPasswordPasswordTextField.clear();
            savedPasswordsCustomPasswordTagTextField.clear();
            savedPasswordsErrorTextField.clear();
        }else {
            savedPasswordsErrorTextField.setText(Errors.SAVED_PASSWORDS_INCORRECTLY_FILLED_FIELDS.getErrorMessage());
        }
    }

    @FXML
    public void onSavedPasswordsDeleteButtonClick() {
        String passwordToDelete = passwordToDeleteTextField.getText();
        String passwordToDeleteTag;

        Pattern pattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(passwordToDelete);

        if (matcher.find()) {
            passwordToDeleteTag = matcher.group(1);
        } else {
            passwordToDeleteTag = "";
        }

        if (!passwordToDelete.isEmpty()) {

            List<Password> passwords = PasswordSaver.loadPasswords();

            List<Password> updatedPasswords = passwords.stream().filter(password ->
                    !password.getPassword().equals(passwordToDelete))
                    .filter(password ->
                            !password.getTag().equals(passwordToDeleteTag))
                    .collect(Collectors.toList());

            PasswordSaver.savePasswords(updatedPasswords);

            updatePasswordsList(updatedPasswords);

            passwordToDeleteTextField.clear();
        }
    }

    @FXML
    public void onSavedPasswordsRefreshButtonClick() {
        List<Password> savedPasswords = PasswordSaver.loadPasswords();
        updatePasswordsList(savedPasswords);
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
