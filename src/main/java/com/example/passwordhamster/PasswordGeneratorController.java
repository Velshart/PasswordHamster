package com.example.passwordhamster;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Random;

enum Errors {
    LENGTH_IS_NOT_A_NUMBER("LENGTH IS NOT A NUMBER"),
    LENGTH_EMPTY("LENGTH FIELD CANNOT BE EMPTY"),
    LENGTH_NOT_PROPERLY_FORMATTED("WRONG LENGTH FORMAT");

    private final String error;

    Errors(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return error;
    }
}

public class PasswordGeneratorController {

    ///////////CHANGE PASSWORD WINDOW///////////
    Stage changePasswordStage = new Stage();
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
    //////////////////////////////////////////


    @FXML
    private TextField generatedPasswordField;

    @FXML
    private Button generatePasswordButton;

    @FXML
    private Button savePasswordButton;

    @FXML
    private Button savedPasswordsButton;

    @FXML
    private TextField passwordLengthField;

    @FXML
    private CheckBox shouldPasswordContainSpecialCharactersCheckBox;

    @FXML
    private CheckBox shouldPasswordContainNumbersCheckBox;

    @FXML
    private CheckBox shouldPasswordContainLowercaseAndUppercaseLettersCheckBox;

    @FXML
    private TextField errorTextField;

    @FXML
    private Button changePasswordButton;

    @FXML
    public void onLengthInput() {
        String input = passwordLengthField.getText();
        try {
            int length = Integer.parseInt(input);

            if (length > 50) {
                passwordLengthField.setText("50");
            } else {
                if (!errorTextField.getText().isEmpty()) errorTextField.setText("");
            }

            if (input.startsWith("0") || input.startsWith("-")) {
                errorTextField.setText(Errors.LENGTH_NOT_PROPERLY_FORMATTED.getErrorMessage());
            }

        } catch (NumberFormatException ex) {
            if (!input.isEmpty()) {
                errorTextField.setText(Errors.LENGTH_IS_NOT_A_NUMBER.getErrorMessage());
            } else {
                errorTextField.setText(Errors.LENGTH_EMPTY.getErrorMessage());
            }
        }
    }

    @FXML
    public void onGenerateButtonClick() {
        if(!passwordLengthField.getText().isEmpty()) {
            generatedPasswordField.setText(
                    generatePassword(Integer.parseInt(passwordLengthField.getText()),
                            shouldPasswordContainSpecialCharactersCheckBox.isSelected(),
                            shouldPasswordContainNumbersCheckBox.isSelected(),
                            shouldPasswordContainLowercaseAndUppercaseLettersCheckBox.isSelected())
            );
            errorTextField.setText("");
        }
    }

    private String generatePassword(int length, boolean canContainSpecialChars, boolean canContainNumbers, boolean canContainUpperLowercaseLetters) {
        StringBuilder generatedPassword = new StringBuilder();
        Random random = new Random();

        List<String> letters = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "z");
        List<String> specialCharacters =  List.of(".", "@", "*", "#", "%", "!", "?", ",", ":");
        List<String> numbers = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> upperLowercaseLetters = List.of("a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "g", "G", "h", "H", "i", "I", "j", "J", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O", "p", "P", "q", "Q", "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y", "z", "Z");



        while (generatedPassword.length() < length) {
            while(true) {
                if (canContainSpecialChars) {

                    if (decide()) {
                        generatedPassword.append(specialCharacters.get(random.nextInt(specialCharacters.size())));
                        break;
                    }
                }else {
                    generatedPassword.append(letters.get(random.nextInt(letters.size())));
                    break;
                }

                if(canContainNumbers) {

                    if(decide()) {
                        generatedPassword.append(numbers.get(random.nextInt(numbers.size())));
                        break;
                    }
                }else {
                    generatedPassword.append(letters.get(random.nextInt(letters.size())));
                    break;
                }

                if(canContainUpperLowercaseLetters) {

                    if(decide()) {
                        generatedPassword.append(upperLowercaseLetters.get(random.nextInt(upperLowercaseLetters.size())));
                        break;
                    }
                }else {
                    generatedPassword.append(letters.get(random.nextInt(letters.size())));
                    break;
                }
            }
        }
        return generatedPassword.toString();
    }

    @FXML
    public void onChangePasswordButtonClick() {
        changePasswordStage.setResizable(false);
        changePasswordStage.setTitle("Change password");

        FXMLLoader loader = new FXMLLoader(PasswordHamster.class.getResource("change-password-view.fxml"));
        try {
            Scene changePasswordScene = new Scene(loader.load(), 364, 236);
            changePasswordStage.setScene(changePasswordScene);

            changePasswordStage.show();

        }catch (IOException ex) {
            ex.printStackTrace();
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
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onSavePasswordButtonClick() {
        if(!generatedPasswordField.getText().isEmpty()) {
            Stage savePasswordStage = new Stage();
            savePasswordStage.setResizable(false);
            savePasswordStage.setTitle("Save password");

            FXMLLoader savePasswordLoader = new FXMLLoader(PasswordHamster.class.getResource("save-password-view.fxml"));

            try {
                Scene savePasswordScene = new Scene(savePasswordLoader.load(), 287, 182);
                savePasswordStage.setScene(savePasswordScene);
                savePasswordStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void onChangePasswordChangeButtonClick() {
        String oldPassword = changePasswordOldPasswordTextField.getText();
        String newPassword = changePasswordNewPasswordField.getText();

        if(oldPassword.equals(newPassword)) {
            //change password

            if(!oldPassword.isEmpty()) {
                changePasswordOldPasswordTextField.setText("");
                changePasswordNewPasswordField.setText("");

                changePasswordErrorTextField.setText(PASSWORD_CHANGED);
            }
        }else {
            changePasswordErrorTextField.setText(PASSWORDS_DIFFERENT_OR_EMPTY);
        }
    }

    private boolean decide() {

        //2 so it will return 0 or 1.
        int range = 2;
        Random random = new Random();

        return random.nextInt(range) == 1;
    }
}