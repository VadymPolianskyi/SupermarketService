package ua.com.polyanski.visual;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import ua.com.polyanski.DBService.ConnectGoodsDB;
import ua.com.polyanski.DBService.ConnectSalesDB;
import ua.com.polyanski.userService.CashRegisterImpl;
import ua.com.polyanski.userService.StringUtilities;
import ua.com.polyanski.userService.data.Good;
import ua.com.polyanski.userService.data.Goods;
import ua.com.polyanski.userService.data.Sale;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class CashRegisterController implements Initializable {


    MainApp mainApp;
    ResourceBundle resourcesBundle;

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    Goods goods = new Goods();

    private String sellerName;
    private String sellerSurname;
    public void setNameSurnameSeller(String sellerName, String sellerSurname) {
        this.sellerName = sellerName;
        this.sellerSurname = sellerSurname;
        initialize();
    }

    Good addGood = null;
    ConnectGoodsDB connectGoodsDB;

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
    Label barCodeExceptionLabel;


    @FXML
    TextField barCodeField;
    @FXML
    TextField numberField;

    @FXML
    Label dataTypeLabel, dataNameLabel, dataModelLabel, dataSaleLabel;

    @FXML
    Button buyButton;

    @Override
    public void initialize(URL location, ResourceBundle resourcesBundle) {
        this.resourcesBundle  = resourcesBundle;
        initialize();
    }


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

        sellerLowLabel.setText( resourcesBundle.getString("seller") + " " + sellerName + " " + sellerSurname);



        bindToTime();
    }



    public void writing() {
        addGood = null;
        if(barCodeField.getText().length() == 8) {
            barCodeExceptionLabel.setText("");
            connectGoodsDB = new ConnectGoodsDB();
            connectGoodsDB.setBarcode(barCodeField.getText());
            Goods show = connectGoodsDB.select();
            if (show.size() == 0) {
                return;
            }
            setVievLabel(show.get(0).getType(), show.get(0).getName(),
                    show.get(0).getModel(), String.valueOf(show.get(0).getPrice()));
            dataTypeLabel.setText(show.get(0).getType());
            dataNameLabel.setText(show.get(0).getName());
            dataModelLabel.setText(show.get(0).getModel());
            dataSaleLabel.setText(String.valueOf(show.get(0).getPrice()));
            addGood = show.get(0);
        } else if ( barCodeField.getText().length() > 8) {
            barCodeExceptionLabel.setText(resourcesBundle.getString("too_many_char_message"));
            setVievLabel("", "", "", "");
        } else if (barCodeField.getText().length() < 8) {
            barCodeExceptionLabel.setText("");
            setVievLabel("", "", "", "");
        }
    }

    public void setVievLabel(String type, String name, String model, String sale) {
        dataTypeLabel.setText(type);
        dataNameLabel.setText(name);
        dataModelLabel.setText(model);
        dataSaleLabel.setText(sale);
    }

    public void add() {
        if (addGood != null) {
            if (numberField.getText().length() == 0) {
                addGood.setNumber(1);
                addGood.newPriceWithSale();
                goodData.add(addGood);
                goods.add(addGood);
                priceLabel.setText(String.valueOf(cashRegister.bill(goods)));
                initialize();
            } else {
                addGood.setNumber(Integer.parseInt(numberField.getText()));
                addGood.newPriceWithSale();
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
        if(!goodData.isEmpty()) {
            ConnectSalesDB connectSalesDB = new ConnectSalesDB();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String data = dateFormat.format(date).replaceAll("/", "-");
            for (Good good : goodData) {
                connectSalesDB.insert(new Sale(0, sellerName, sellerSurname, good.getName(), good.getPrice(), data, good.getNumber()));
            }

            mainApp.setPrice(cashRegister.bill(goods));
            mainApp.showWindow("remainder.fxml", resourcesBundle.getString("remainder_title"));
            goodData.clear();
            barCodeField.setText("");
            setVievLabel("", "", "", "");
            priceLabel.setText("00.0");
            initialize();
            // open inform dialog with
        } else {
            mainApp.setMessage(resourcesBundle.getString("add_goods"));
            mainApp.showWindow("informSecond.fxml", resourcesBundle.getString("information_message"));
        }
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
                                timelowLabel.setText("|  "+ resourcesBundle.getString("time")+ "  " +hourString + ":" + minuteString + ":" + secondString);
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void languageUkraineItem() {
        mainApp.setLocalLanguage("ukr");
        buyButton.getScene().getWindow().hide();
        mainApp.showWindow("cashRegister.fxml", "Касовий апарат");
    }
    public void languageRussianItem() {
        mainApp.setLocalLanguage("rus");
        buyButton.getScene().getWindow().hide();
        mainApp.showWindow("cashRegister.fxml", "Кассовый аппарат");
    }
    public void languageEnglishItem() {
        mainApp.setLocalLanguage("en");
        buyButton.getScene().getWindow().hide();
        mainApp.showWindow("cashRegister.fxml", "Cash register");
    }

    public void aboutItem() {
        mainApp.showWindow("about.fxml", resourcesBundle.getString("service_about"));
    }

    public void helpItem() {
        mainApp.showWindow("reference.fxml", resourcesBundle.getString("help"));
    }

    public void exitItem() {
        mainApp.setCloseButton(buyButton);
        mainApp.informationDilogYesNo(buyButton, resourcesBundle.getString("want_Exit"));
    }
}
