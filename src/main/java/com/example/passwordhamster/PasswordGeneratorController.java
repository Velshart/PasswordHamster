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
    private CheckBox shouldPasswordContainSpecialCharactersCheckBox;

    @FXML
    private CheckBox shouldPasswordContainNumbersCheckBox;

    @FXML
    private CheckBox shouldPasswordContainLowercaseAndUppercaseLettersCheckBox;

    @FXML
    private TextField errorTextField;

    @FXML
    public void onLengthInput() {
        String input = passwordLengthField.getText();
        try {
            int length = Integer.parseInt(input);

            if(length > 50) {
                passwordLengthField.setText("50");
            }else {
                if(!errorTextField.getText().isEmpty()) errorTextField.setText("");
            }

            if(input.startsWith("0") || input.startsWith("-")) {
                errorTextField.setText(Errors.LENGTH_NOT_PROPERLY_FORMATTED.getErrorMessage());
            }

        }catch(NumberFormatException ex) {
            if(!input.isEmpty()) {
                errorTextField.setText(Errors.LENGTH_IS_NOT_A_NUMBER.getErrorMessage());
            }else {
                errorTextField.setText(Errors.LENGTH_EMPTY.getErrorMessage());
            }
        }
    }
}

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