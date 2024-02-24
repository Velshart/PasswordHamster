package com.example.passwordhamster;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
}
