package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ua.com.polyanski.DBService.ConnectSellersDB;
import ua.com.polyanski.userService.data.Seller;

/**
 * Created by vadym on 13.11.16.
 */
public class RegistrationController {

    MainApp mainApp;

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    TextField birthdayField;
    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;

    @FXML
    Button signUpButton;
    public void sig() {
        if (nameField.getText() != null || surnameField.getText() != null || birthdayField.getText() != null ||
                loginField.getText() != null || passwordField.getText() != null) {
            Seller seller = new Seller(0, nameField.getText(), surnameField.getText(), birthdayField.getText(),
                    loginField.getText(), passwordField.getText(), 0);

            ConnectSellersDB connectSellersDB = new ConnectSellersDB();
            connectSellersDB.insert(seller);
        }
    }
}
