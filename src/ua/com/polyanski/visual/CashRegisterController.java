package ua.com.polyanski.visual;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ua.com.polyanski.DBService.ConnectMainDB;
import ua.com.polyanski.userService.CashRegister;
import ua.com.polyanski.userService.CashRegisterImpl;
import ua.com.polyanski.userService.StringUtilities;
import ua.com.polyanski.userService.data.Good;
import ua.com.polyanski.userService.data.Goods;

import java.util.Calendar;

public class CashRegisterController {

    Goods goods = new Goods();

    Good addGood = null;
    ConnectMainDB connectMainDB;

    private ObservableList<Good> goodData = FXCollections.observableArrayList();
    CashRegisterImpl cashRegister = new CashRegisterImpl();

    @FXML
    TableView<Good> dataTable;

    @FXML
    TableColumn<Good, String> barCodeColum;
    @FXML
    TableColumn<Good, String> typeColum;
    @FXML
    TableColumn<Good, String> nameColum;
    @FXML
    TableColumn<Good, String> modelColum;
    @FXML
    TableColumn<Good, String> numberColum;
    @FXML
    TableColumn<Good, Double> priceColum;
    @FXML
    Label priceLabel;
    @FXML
    Label sellerLowLabel;
    @FXML
    Label timelowLabel;


    @FXML
    TextField barCodeField;
    @FXML
    TextField numberField;

    @FXML
    Label dataTypeLabel, dataNameLabel, dataModelLabel, dataSaleLabel;


    @FXML
    private void initialize() {
//        sellerLowLabel  --> set seller

        barCodeColum.setCellValueFactory(new PropertyValueFactory<Good,String>("barcode"));
        typeColum.setCellValueFactory(new PropertyValueFactory<Good,String>("type"));
        nameColum.setCellValueFactory(new PropertyValueFactory<Good,String>("name"));
        modelColum.setCellValueFactory(new PropertyValueFactory<Good,String>("model"));
        numberColum.setCellValueFactory(new PropertyValueFactory<Good,String>("number"));
        priceColum.setCellValueFactory(new PropertyValueFactory<Good,Double>("price"));

        dataTable.setItems(goodData);

        bindToTime();
    }

    private void initData() {
//        ConnectMainDB connectMainDB = new ConnectMainDB();
//        Goods goods = new Goods();
//        goods = connectMainDB.select();
//
//        for (int i = 0; i < goods.size(); i++ ) {
//            goodData.add(goods.get(i));
//        }
    }

    public void writing() {
        if(barCodeField.getText().length() == 8) {
            connectMainDB= new ConnectMainDB();
            connectMainDB.setBarcode(barCodeField.getText());
            Goods show = connectMainDB.select();
            if (show.size() == 0) {
                return;
            }
            dataTypeLabel.setText(show.get(0).getType());
            dataNameLabel.setText(show.get(0).getName());
            dataModelLabel.setText(show.get(0).getModel());
            dataSaleLabel.setText(String.valueOf(show.get(0).getPrice()));
            addGood = show.get(0);
        }else {
            System.out.println(barCodeField.getText().length());
        }
    }

    public void add() {
        if (addGood == null) {

        } else {
            if (numberField.getText().length() == 0) {
                addGood.setNumber(1);
                goodData.add(addGood);
                goods.add(addGood);
                priceLabel.setText(String.valueOf(cashRegister.bill(goods)));
                initialize();
            } else {
                addGood.setNumber(Integer.parseInt(numberField.getText()));
                goodData.add(addGood);
                goods.add(addGood);
                priceLabel.setText(String.valueOf(cashRegister.bill(goods)));
                initialize();
            }
        }
        addGood = null;
    }

    public void clear(){
        goodData.clear();
        goods = new Goods();
        priceLabel.setText("0.00");
        initialize();
    }

    public void delete() {
        if (dataTable.getSelectionModel().getSelectedItem()!= null) {
            Good selectGood = dataTable.getSelectionModel().getSelectedItem();
            goodData.remove(selectGood);
            goods.remove(dataTable.getSelectionModel().getSelectedIndex());
            priceLabel.setText(String.valueOf(cashRegister.bill(goods)));
            initialize();
        } else {
        }
    }

    public void buy() {

        System.out.println(cashRegister.bill(goods));
        // open inform dialog with
    }

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                String hourString = StringUtilities.pad(2, ' ', time.get(Calendar.HOUR_OF_DAY) == 0 ? "12" : time.get(Calendar.HOUR_OF_DAY) + "");
                                String minuteString = StringUtilities.pad(2, '0', time.get(Calendar.MINUTE) + "");
                                String secondString = StringUtilities.pad(2, '0', time.get(Calendar.SECOND) + "");
//                                String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                                timelowLabel.setText("|  Time:  " +hourString + ":" + minuteString + ":" + secondString);
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
