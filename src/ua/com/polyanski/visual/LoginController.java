package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.com.polyanski.userService.PasswordService;

/**
 * Created by vadym on 13.11.16.
 */
public class LoginController {
    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;
    PasswordService passwordService = new PasswordService();

    public void sigIn() {
        if (passwordService.checkPassword(loginField.getText(), passwordField.getText())) {
            //open window
        } else {
            loginField.setText("");
            passwordField.setText("");
            //open another
        }
    }

    public void sigUp() {
        //open registration
    }

    public void sigInAdmin() {
        //open passwordAdm
    }
}
