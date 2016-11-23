package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by vadym on 20.11.2016.
 */
public class RemainderController implements Initializable {
    ResourceBundle resourceBundle;

    @FXML
    Label remainderLabel;

    @FXML
    Label informLabel;

    @FXML
    Label priceLabel;

    @FXML
    TextField moneyField;

    @FXML
    Button completeButton;
    double price;



    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public void setPrice(double price) {
        this.price = price;
        initialize();
    }


    @FXML
    private void initialize() {
        priceLabel.setText( resourceBundle.getString("price_sec") + " (" + price + ")");
    }



    private boolean isPayed = false;

    public void completeClick() {
        if (isPayed) {
            completeButton.getScene().getWindow().hide();
        } else {
            informLabel.setText(resourceBundle.getString("insert_money"));
        }
    }

    public void payClick() {
        if (moneyField.getText() != null) {
            double getMoney = Double.parseDouble(moneyField.getText());
            if (getMoney >= price) {
                informLabel.setText(resourceBundle.getString("thank"));
                remainderLabel.setText(String.valueOf(getMoney - price));
                isPayed = true;
            } else {
                informLabel.setText(resourceBundle.getString("not_enought_mon"));
            }
        }
    }
}
