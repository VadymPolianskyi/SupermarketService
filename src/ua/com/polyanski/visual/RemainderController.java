package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by vadym on 20.11.2016.
 */
public class RemainderController {
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

    public void setPrice(double price) {
        this.price = price;
        initialize();
    }


    @FXML
    private void initialize() {
        System.out.println("initialize: " + price);
        priceLabel.setText("Price: (" + price + ")");
    }



    private boolean isPayed = false;

    public void completeClick() {
        if (isPayed) {
            completeButton.getScene().getWindow().hide();
        } else {
            informLabel.setText("Please, insert money!");
        }
    }

    public void payClick() {
        if (moneyField.getText() != null) {
            if (Double.parseDouble(moneyField.getText()) >= price) {
                informLabel.setText("Thank you!");
                isPayed = true;
            } else {
                informLabel.setText("Not enough money..");
            }
        }
    }
}
