package ua.com.polyanski.visual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.com.polyanski.DBService.ConnectAnotherDB;
import ua.com.polyanski.DBService.ConnectGoodsDB;
import ua.com.polyanski.userService.data.Good;
import ua.com.polyanski.userService.data.Goods;
import ua.com.polyanski.userService.data.Seller;

/**
 * Created by vadym on 13.11.16.
 */
public class AddGoodsAndSellersController {
    private ObservableList<Good> goodData = FXCollections.observableArrayList();
    private ObservableList<Seller> sellerData = FXCollections.observableArrayList();
    MainApp mainApp;
    ConnectGoodsDB connectGoodsDB;
    ConnectAnotherDB connectAnotherDB;
    Good selectGood = null;
    int maxID = 0;

    @FXML
    TextField barCodeField, numberField, priceField, saleField, expirationDateField;
    @FXML
    TextField nameSecondField, surnameSecondField, birthSecondField, loginSecondField, oldPasswordField,
            newPasswordField, newPasswordAgainField;

    @FXML
    ComboBox typeComboBox, modelComboBox, nameComboBox;
    @FXML
    TableView<Good> dataTableViev;

    @FXML
    TableColumn<Good, String> barCodeColum;
    @FXML
    TableColumn<Good, String> typeColum;
    @FXML
    TableColumn<Good, String> nameColum;
    @FXML
    TableColumn<Good, String> modelColum;
    @FXML
    TableColumn<Good, Double> priceColum;
    @FXML
    TableColumn<Good, Integer> numberColum;
    @FXML
    TableColumn<Good, Integer> saleColum;

    @FXML
    TableView<Seller> thirdDataTable;

    @FXML
    TableColumn<Seller, String> nameSellerColumn, surnameColumn, birthColumn, loginColumn, salesToMonth;

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        goodData.clear();
        addToComboBoxData();

        initDataGoods();
        barCodeColum.setCellValueFactory(new PropertyValueFactory<Good,String>("barcode"));
        typeColum.setCellValueFactory(new PropertyValueFactory<Good,String>("type"));
        nameColum.setCellValueFactory(new PropertyValueFactory<Good,String>("name"));
        modelColum.setCellValueFactory(new PropertyValueFactory<Good,String>("model"));
        numberColum.setCellValueFactory(new PropertyValueFactory<Good,Integer>("number"));
        priceColum.setCellValueFactory(new PropertyValueFactory<Good,Double>("price"));
        saleColum.setCellValueFactory(new PropertyValueFactory<Good,Integer>("sale"));

        dataTableViev.setItems(goodData);

        initDataSellers();

        dataTableViev.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showFirstSelectedData(newValue)));
        thirdDataTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showSecondSelectedData(newValue)));
    }

    private void initDataSellers() {
    }

    private void showSecondSelectedData(Seller newValue) {

    }

    private void showFirstSelectedData(Good selectGood) {

        if(selectGood == null){
        } else {
            this.selectGood = selectGood;

            barCodeField.setText(selectGood.getBarcode());
            numberField.setText(String.valueOf(selectGood.getNumber()));
            priceField.setText(String.valueOf(selectGood.getPrice()));
            saleField.setText(String.valueOf(selectGood.getSale()));
            expirationDateField.setText(selectGood.getExpiration_date());
            typeComboBox.setValue(selectGood.getType());
            nameComboBox.setValue(selectGood.getName());
            modelComboBox.setValue(selectGood.getModel());
        }
    }

    private void initDataGoods() {
        ConnectGoodsDB connectGoodsDB = new ConnectGoodsDB();
        Goods goods = new Goods();
        goods = connectGoodsDB.select();

        for (int i = 0; i < goods.size(); i++ ) {
            goodData.add(goods.get(i));
            maxID = goods.get(i).getId();
        }
        System.out.println(goods.size());
    }

    private void addToComboBoxData() {
        connectAnotherDB = new ConnectAnotherDB("spr_Name", "name");
        nameComboBox.getItems().setAll(connectAnotherDB.select());


        connectAnotherDB = new ConnectAnotherDB("spr_Type", "nameType");
        typeComboBox.getItems().setAll(connectAnotherDB.select());

        connectAnotherDB = new ConnectAnotherDB("spr_Model", "nameModel");
        modelComboBox.getItems().setAll(connectAnotherDB.select());
    }

    public void changeGoods() {
        if (selectGood != null) {
            connectGoodsDB = new ConnectGoodsDB();
            try {
                connectGoodsDB.update(new Good(barCodeField.getText(), selectGood.getId(), typeComboBox.getValue().toString(), nameComboBox.getValue().toString(),
                        modelComboBox.getValue().toString(), expirationDateField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(numberField.getText()),
                        Integer.parseInt(saleField.getText()), 0));
                initialize();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException...");
            }
        }
        else{

        }
    }

    public void addGoods() {
        try {

                ConnectGoodsDB connectGoodsDB = new ConnectGoodsDB();
                Good good = new Good(barCodeField.getText(), maxID + 1, typeComboBox.getValue().toString(), nameComboBox.getValue().toString(),
                        modelComboBox.getValue().toString(), expirationDateField.getText(), Double.parseDouble(priceField.getText()),
                        Integer.parseInt(numberField.getText()), Integer.parseInt(saleField.getText()), 0);
                connectGoodsDB.insert(good);
                goodData.add(good);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException..");
        }

    }

    public void closeGoods() {
        System.out.println("close");
    }

    public void changeSellers() {
        System.out.println("change");
    }

    public void addSellers() {
        System.out.println("add");
    }

    public void closeSellers(){
        closeGoods();
    }

    public void changePassword() {
        System.out.println("changePassword");
    }
}
