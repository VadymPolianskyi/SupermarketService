package ua.com.polyanski.visual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.com.polyanski.DBService.ConnectAnotherDB;
import ua.com.polyanski.DBService.ConnectGoodsDB;
import ua.com.polyanski.DBService.ConnectSellersDB;
import ua.com.polyanski.userService.data.Good;
import ua.com.polyanski.userService.data.Goods;
import ua.com.polyanski.userService.data.Seller;
import ua.com.polyanski.userService.data.Sellers;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by vadym on 13.11.16.
 */
public class AddGoodsAndSellersController implements Initializable {

    private ObservableList<Good> goodData = FXCollections.observableArrayList();
    private ObservableList<Seller> sellerData = FXCollections.observableArrayList();
    MainApp mainApp;
    ConnectGoodsDB connectGoodsDB;
    ConnectSellersDB connectSellersDB;
    ConnectAnotherDB connectAnotherDB;
    Good selectGood = null;
    Seller selectSeller = null;
    int maxIDGood = 0;
    int maxIDSeller = 0;

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

    @FXML
    Button closeButton;

    ResourceBundle resourceBundle;
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        initialize();
    }

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        goodData.clear();
        sellerData.clear();
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
        nameSellerColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("surname"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("birthday"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("login"));
        salesToMonth.setCellValueFactory(new PropertyValueFactory<Seller, String>("sales"));

        thirdDataTable.setItems(sellerData);

        dataTableViev.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showFirstSelectedData(newValue)));
        thirdDataTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showSecondSelectedData(newValue)));
    }

    private void initDataSellers() {
        ConnectSellersDB connectSellersDB = new ConnectSellersDB();
        Sellers sellers = connectSellersDB.select();

        for (int i = 0; i < sellers.size(); i++ ) {
            sellerData.add(sellers.get(i));
            maxIDSeller = sellers.get(i).getId();
        }
    }

    private void showSecondSelectedData(Seller selectSeller) {
        if (selectSeller == null) {
        } else {
            this.selectSeller = selectSeller;
            nameSecondField.setText(selectSeller.getName());
            surnameSecondField.setText(selectSeller.getSurname());
            birthSecondField.setText(selectSeller.getBirthday());
            loginSecondField.setText((selectSeller.getLogin()));
        }

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
        Goods goods = connectGoodsDB.select();

        for (int i = 0; i < goods.size(); i++ ) {
            goodData.add(goods.get(i));
            maxIDGood = goods.get(i).getId();
        }
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
                connectGoodsDB.update(new Good(barCodeField.getText(), selectGood.getId(),
                        typeComboBox.getValue().toString(), nameComboBox.getValue().toString(),
                        modelComboBox.getValue().toString(), correctData(expirationDateField.getText()),
                        Double.parseDouble(priceField.getText()), Integer.parseInt(numberField.getText()),
                        Integer.parseInt(saleField.getText()), 0));
                initialize();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException...");
            }
        }
    }

    public void deleteGood() {
        if (selectGood != null) {
            connectGoodsDB = new ConnectGoodsDB();
            try {
                connectGoodsDB.delete(selectGood.getId());
                initialize();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException...");
            }
        }
    }
    public void changeSellers() {
        if (selectSeller != null) {
            connectSellersDB = new ConnectSellersDB();
            try {
                connectSellersDB.update(new Seller(selectSeller.getId(), nameSecondField.getText(),
                        surnameSecondField.getText(), correctData(birthSecondField.getText()),
                            loginSecondField.getText(), selectSeller.getPassword(), 0));
                initialize();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException...");
            }
        }
    }

    public void deleteSeller() {
        if (selectSeller != null) {
            connectSellersDB = new ConnectSellersDB();
            try {
                connectSellersDB.delete(selectSeller.getId());
                initialize();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException...");
            }
        }    }

    public void changePassword() {
        if (selectSeller != null|| oldPasswordField.getText() != null || oldPasswordField.getText() == newPasswordField.getText() ||
                newPasswordField.getText() == newPasswordAgainField.getText()) {

            connectSellersDB = new ConnectSellersDB();
            connectSellersDB.update(new Seller(selectSeller.getId(), selectSeller.getName(),
                    selectSeller.getSurname(), selectSeller.getBirthday(), selectSeller.getLogin(),
                        newPasswordField.getText(), 0));

            oldPasswordField.clear();
            newPasswordField.clear();
            newPasswordAgainField.clear();
        }

    }



    public void addGoods() {
            try {

                ConnectGoodsDB connectGoodsDB = new ConnectGoodsDB();
                Good good = new Good(barCodeField.getText(), maxIDGood + 1, typeComboBox.getValue().toString(), nameComboBox.getValue().toString(),
                        modelComboBox.getValue().toString(), correctData(expirationDateField.getText()), Double.parseDouble(priceField.getText()),
                        Integer.parseInt(numberField.getText()), Integer.parseInt(saleField.getText()), 0);
                connectGoodsDB.insert(good);
                goodData.add(good);
            } catch (NullPointerException e) {
                mainApp.setMessage(resourceBundle.getString("fill_fields"));
                mainApp.showWindow("informSecond.fxml", resourceBundle.getString("information_message"));
            }
    }
    public void addSellers() {
            try {
                ConnectSellersDB connectSellersDB = new ConnectSellersDB();
                Seller seller = new Seller(maxIDSeller + 1, nameSecondField.getText(), surnameSecondField.getText(),
                        correctData(birthSecondField.getText()), loginSecondField.getText(),
                        newPasswordField.getText(), 0);
                connectSellersDB.insert(seller);

                sellerData.add(seller);
            } catch (NullPointerException e) {
                mainApp.setMessage(resourceBundle.getString("fill_fields"));
                mainApp.showWindow("informSecond.fxml", resourceBundle.getString("information_message"));
            }
    }

    public void closeGoods() {
        closeButton.getScene().getWindow().hide();
        //login open
    }


    public void closeSellers(){
        closeGoods();
    }


    public String correctData(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = formatter.parse(dateInString);
            return formatter.format(date);

        } catch (ParseException e) {
            mainApp.setMessage(resourceBundle.getString("not_correct_date"));
            mainApp.showWindow("informSecond.fxml", resourceBundle.getString("information_message"));
        }
        return null;
    }

    public void tableSalesItem() {
        mainApp.showWindow("tableSales.fxml", resourceBundle.getString("table_sales"));
    }

    public void addItem() {
        mainApp.showWindow("addTypeNameModel.fxml", resourceBundle.getString("add_type_name_model"));
    }

    public void exitItem() {
        mainApp.setCloseButton(closeButton);
        mainApp.informationDilogYesNo(closeButton, resourceBundle.getString("want_Exit"));
    }
//---------------------------------------------
public void languageUkraineItem() {
        mainApp.setLocalLanguage("ukr");
        closeButton.getScene().getWindow().hide();
        mainApp.showWindow("addGoodsAndSellers.fxml", "Адміністратор");
}
    public void languageRussianItem() {
        mainApp.setLocalLanguage("rus");
        closeButton.getScene().getWindow().hide();
        mainApp.showWindow("addGoodsAndSellers.fxml", "Администратор");
    }
    public void languageEnglishItem() {
        mainApp.setLocalLanguage("en");
        closeButton.getScene().getWindow().hide();
        mainApp.showWindow("addGoodsAndSellers.fxml", "Administrator");
    }
//---------------------------------------------


    public void aboutItem() {
        mainApp.showWindow("about.fxml", resourceBundle.getString("service_about"));
    }

    public void helpItem() {
        mainApp.showWindow("reference.fxml", resourceBundle.getString("help"));
    }
}
