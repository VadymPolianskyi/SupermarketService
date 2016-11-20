package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ua.com.polyanski.userService.PasswordService;

/**
 * Created by vadym on 13.11.16.
 */
public class AdminPassController {

    MainApp mainApp;

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    TextField passwordAdminField;
    @FXML
    Button signInButton;

    public void checkPass() {
        PasswordService passServ = new PasswordService();
        if(passServ.checkAdminPassword(passwordAdminField.getText())) {
            mainApp.showWindow("addGoodsAndSellers.fxml", "Administrator");
            signInButton.getScene().getWindow().hide();
        } else {
            passwordAdminField.clear();
        }
    }
}
