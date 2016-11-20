package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Created by vadym on 13.11.16.
 */
public class InformSecondController {

    @FXML
    Button okButton;
    @FXML
    Label informationMessageLabel;

    public void ok() {
        okButton.getScene().getWindow().hide();
    }

    public void setMessage(String message) {
        informationMessageLabel.setText(message);
    }


}
