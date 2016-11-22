package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ua.com.polyanski.DBService.ConnectAnotherDB;
import ua.com.polyanski.DBService.ConnectSellersDB;
import ua.com.polyanski.userService.PasswordService;
import ua.com.polyanski.userService.data.Sellers;

/**
 * Created by vadym on 13.11.16.
 */
public class LoginController {

    MainApp mainApp;
    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;
    @FXML
    Button sigInButton;
    @FXML
    Button adminButton;

    PasswordService passwordService = new PasswordService();

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void sigIn() {
        if (loginField.getText() != null|| passwordField.getText()!= null) {
            if (passwordService.checkPassword(loginField.getText(), passwordField.getText())) {
                ConnectSellersDB sellersDB = new ConnectSellersDB();
                sellersDB.setToSearchLogin(loginField.getText());
                Sellers sellers = sellersDB.select();
                mainApp.setNameSurnameSeller(sellers.get(0).getName(),sellers.get(0).getSurname());
                sigInButton.getScene().getWindow().hide();
                mainApp.showWindow("cashRegister.fxml", "Cash Register");

            } else {
                passwordField.setText("");
                //show another
            }
        }
    }

    public void sigUp() {
        mainApp.showWindow("registration.fxml", "Registration seller");
    }

    public void sigInAdmin() {
        adminButton.getScene().getWindow().hide();
        mainApp.showWindow("adminPass.fxml", "Admin sign in");
    }
}
