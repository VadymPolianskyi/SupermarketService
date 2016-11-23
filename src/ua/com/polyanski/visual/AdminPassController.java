package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ua.com.polyanski.userService.PasswordService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by vadym on 13.11.16.
 */
public class AdminPassController implements Initializable {

    MainApp mainApp;

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    TextField passwordAdminField;
    @FXML
    Button signInButton;

    @FXML
    Label exceptionLabel;


    ResourceBundle resourceBundle;

    public void checkPass() {
        PasswordService passServ = new PasswordService();
        if(passServ.checkAdminPassword(passwordAdminField.getText())) {
            mainApp.showWindow("addGoodsAndSellers.fxml", resourceBundle.getString("admin"));
            signInButton.getScene().getWindow().hide();
            exceptionLabel.setText("");
        } else {
            passwordAdminField.clear();
            exceptionLabel.setText(resourceBundle.getString("wrong_password"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
}
