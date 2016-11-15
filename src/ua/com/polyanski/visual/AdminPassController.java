package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.com.polyanski.userService.PasswordService;

/**
 * Created by vadym on 13.11.16.
 */
public class AdminPassController {
    @FXML
    TextField passwordAdminField;

    public void checkPass() {
        PasswordService passServ = new PasswordService();
        if(passServ.checkAdminPassword(passwordAdminField.getText())) {
            //open addGodsAndSellers
        } else {
            passwordAdminField.clear();
        }
    }
}
