package com.example.passwordhamster;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class PasswordGeneratorController {
    @FXML
    private TextField generatedPasswordField;

    @FXML
    private Button generatePasswordButton;

    @FXML
    private Button savePasswordButton;

    @FXML
    private TextField passwordLengthField;

    @FXML
    private CheckBox specialCharactersCheckBox;

    @FXML
    private CheckBox shouldPasswordContainSpecialCharacters;

    @FXML
    private CheckBox shouldPasswordContainNumbersCheckBox;

    @FXML
    private CheckBox shouldPasswordContainLowercaseAndUppercaseLettersCheckBox;

    @FXML
    public void onLengthInput() {
    }
}