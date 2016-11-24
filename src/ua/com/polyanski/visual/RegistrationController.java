package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ua.com.polyanski.DBService.ConnectSellersDB;
import ua.com.polyanski.userService.data.Seller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
//    @FXML
//    TextField birthdayField;

    @FXML
    DatePicker dateBirth;
    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;

    @FXML
    Button signUpButton;
    public void sig() {
        if (nameField.getText() != null || surnameField.getText() != null || dateBirth.getValue() != null ||
                loginField.getText() != null || passwordField.getText() != null) {
            Seller seller = new Seller(0, nameField.getText(), surnameField.getText(), getDateFromDP(),
                    loginField.getText(), passwordField.getText(), 0);

            ConnectSellersDB connectSellersDB = new ConnectSellersDB();
            connectSellersDB.insert(seller);

            nameField.clear();
            surnameField.clear();
            dateBirth.setValue(null);
            loginField.clear();
            passwordField.clear();
        }
    }

    public String getDateFromDP() {
        LocalDate localDate = dateBirth.getValue();
//        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
////
//        System.out.println(localDate);
//        return format.format(localDate);
        return String.valueOf(localDate);
    }
}
