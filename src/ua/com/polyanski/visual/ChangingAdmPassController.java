package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ua.com.polyanski.DBService.ConnectAdminDB;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by vadym on 24.11.2016.
 */
public class ChangingAdmPassController implements Initializable {

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    MainApp mainApp;

    @FXML
    TextField oldPasswordField, newFirstPasswordField, newSecondPasswordField;
    @FXML
    Button cancelButton;

    public void changeClick() {

        if (oldPasswordField.getText() != null && newFirstPasswordField.getText() != null
                    && newSecondPasswordField.getText() != null) {

            ConnectAdminDB connectAdminDB = new ConnectAdminDB();
            if (oldPasswordField.getText().equals(connectAdminDB.select().get(1)) &&
                    newFirstPasswordField.getText().equals(newSecondPasswordField.getText())){
                    connectAdminDB.update(newFirstPasswordField.getText());
            } else {
                mainApp.setMessage(resourceBundle.getString("enter_correct_passwords"));
                mainApp.showWindow("informSecond.fxml", resourceBundle.getString("information_message"));
            }
        }

    }

    public void cancelClick() {
        cancelButton.getScene().getWindow().hide();
    }

    ResourceBundle resourceBundle;
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
}
