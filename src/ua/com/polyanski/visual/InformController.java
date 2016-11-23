package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by vadym on 13.11.16.
 */
public class InformController {
    @FXML
    Button closeButton;
    @FXML
    Button noButton;
    @FXML
    Button yesButton;

    @FXML
    Label informationMessageLabel;

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    MainApp mainApp;

    public void setMessage(String message) {
        informationMessageLabel.setText(message);
    }

    public void setCloseButton(Button closeButton) {
        this.closeButton = closeButton;
    }
    public void yes() {
        System.out.println("yes");
        yesButton.getScene().getWindow().hide();
        closeButton.getScene().getWindow().hide();
        mainApp.showWindow("login.fxml", "Sign in");
    }
    public void  no() {
        noButton.getScene().getWindow().hide();
    }
}
