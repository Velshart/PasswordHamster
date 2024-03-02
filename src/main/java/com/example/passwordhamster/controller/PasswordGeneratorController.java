package com.example.passwordhamster.controller;


import com.example.passwordhamster.enumeration.Errors;
import com.example.passwordhamster.PasswordHamster;
import com.example.passwordhamster.object.Password;
import com.example.passwordhamster.json.PasswordSaver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Random;


public class PasswordGeneratorController {
    @FXML
    private TextField generatedPasswordField;

    @FXML
    private TextField passwordGeneratorPasswordLengthField;

    @FXML
    private CheckBox shouldPasswordContainSpecialCharactersCheckBox;

    @FXML
    private CheckBox shouldPasswordContainNumbersCheckBox;

    @FXML
    private CheckBox shouldPasswordContainLowercaseAndUppercaseLettersCheckBox;

    @FXML
    private TextField passwordGeneratorErrorTextField;

    @FXML
    private TextField passwordGeneratorTagTextField;

    private String generatedPasswd;

    @FXML
    public void initialize() {
        passwordGeneratorErrorTextField.setStyle("-fx-text-inner-color: red;");
    }

    @FXML
    public void onLengthInput() {
        String input = passwordGeneratorPasswordLengthField.getText();
        try {
            int length = Integer.parseInt(input);

            if (length > 50) {
                passwordGeneratorPasswordLengthField.setText("50");
            } else {
                if (!passwordGeneratorErrorTextField.getText().isEmpty()) passwordGeneratorErrorTextField.clear();
            }

            if (input.startsWith("0") || input.startsWith("-")) {
                passwordGeneratorErrorTextField.setText(Errors.LENGTH_NOT_PROPERLY_FORMATTED.getErrorMessage());
            }

        } catch (NumberFormatException ex) {
            if (!input.isEmpty()) {
                passwordGeneratorErrorTextField.setText(Errors.LENGTH_IS_NOT_A_NUMBER.getErrorMessage());
            } else {
                passwordGeneratorErrorTextField.setText(Errors.LENGTH_EMPTY.getErrorMessage());
            }
        }
    }

    @FXML
    public void onGenerateButtonClick() {
        if (!passwordGeneratorPasswordLengthField.getText().isEmpty()) {
            generatedPasswd = generatePassword(Integer.parseInt(passwordGeneratorPasswordLengthField.getText()),
                    shouldPasswordContainSpecialCharactersCheckBox.isSelected(),
                    shouldPasswordContainNumbersCheckBox.isSelected(),
                    shouldPasswordContainLowercaseAndUppercaseLettersCheckBox.isSelected());

            passwordGeneratorPasswordLengthField.clear();

            shouldPasswordContainSpecialCharactersCheckBox.setSelected(false);
            shouldPasswordContainLowercaseAndUppercaseLettersCheckBox.setSelected(false);
            shouldPasswordContainNumbersCheckBox.setSelected(false);
        }
        generatedPasswordField.setText(generatedPasswd);
    }

    private String generatePassword(int length, boolean canContainSpecialChars, boolean canContainNumbers, boolean canContainUpperLowercaseLetters) {
        StringBuilder generatedPassword = new StringBuilder();
        Random random = new Random();

        List<String> letters = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "z");
        List<String> specialCharacters = List.of(".", "@", "*", "#", "%", "!", "?", ",", ":");
        List<String> numbers = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> upperLowercaseLetters = List.of("a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "g", "G", "h", "H", "i", "I", "j", "J", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O", "p", "P", "q", "Q", "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y", "z", "Z");


        while (generatedPassword.length() < length) {
            while (true) {
                if (canContainSpecialChars) {

                    if (decide()) {
                        generatedPassword.append(specialCharacters.get(random.nextInt(specialCharacters.size())));
                        break;
                    }
                } else {
                    generatedPassword.append(letters.get(random.nextInt(letters.size())));
                    break;
                }

                if (canContainNumbers) {

                    if (decide()) {
                        generatedPassword.append(numbers.get(random.nextInt(numbers.size())));
                        break;
                    }
                } else {
                    generatedPassword.append(letters.get(random.nextInt(letters.size())));
                    break;
                }

                if (canContainUpperLowercaseLetters) {

                    if (decide()) {
                        generatedPassword.append(upperLowercaseLetters.get(random.nextInt(upperLowercaseLetters.size())));
                        break;
                    }
                } else {
                    generatedPassword.append(letters.get(random.nextInt(letters.size())));
                    break;
                }
            }
        }
        return generatedPassword.toString();
    }

    @FXML
    public void onChangePasswordButtonClick() {
        Stage changePasswordStage = new Stage();
        changePasswordStage.setResizable(false);
        changePasswordStage.setTitle("Change password");

        FXMLLoader loader = new FXMLLoader(PasswordHamster.class.getResource("change-password-view.fxml"));
        try {
            Scene changePasswordScene = new Scene(loader.load(), 364, 236);
            changePasswordStage.setScene(changePasswordScene);

            changePasswordStage.show();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @FXML
    public void onSavedPasswordsButtonClick() {
            Stage savedPasswordsLoginStage = new Stage();
            savedPasswordsLoginStage.setResizable(false);
            savedPasswordsLoginStage.setTitle("Saved passwords");

            FXMLLoader loader = new FXMLLoader(PasswordHamster.class.getResource("saved-passwords-login-view.fxml"));

            try {
                Scene savedPasswordsLoginScene = new Scene(loader.load(), 277, 133);
                savedPasswordsLoginStage.setScene(savedPasswordsLoginScene);
                savedPasswordsLoginStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }

    @FXML
    public void onSavePasswordButtonClick() {
        try {
            if (!generatedPasswordField.getText().isEmpty() && !passwordGeneratorTagTextField.getText().isEmpty()) {
                List<Password> passwords = PasswordSaver.loadPasswords();

                passwords.add(new Password(passwordGeneratorTagTextField.getText(), generatedPasswordField.getText()));
                PasswordSaver.savePasswords(passwords);

                passwordGeneratorTagTextField.clear();
                generatedPasswordField.clear();
                passwordGeneratorErrorTextField.clear();
            } else {
                passwordGeneratorErrorTextField.setText(Errors.TAG_NOT_PROVIDED.getErrorMessage());
            }
        }catch (NullPointerException ex) {
            //ignored
        }
    }

    private boolean decide() {

        //2 so it will return 0 or 1.
        int range = 2;
        Random random = new Random();

        return random.nextInt(range) == 1;
    }
}