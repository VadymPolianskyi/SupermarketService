package ua.com.polyanski.visual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import ua.com.polyanski.DBService.ConnectMainDB;
import ua.com.polyanski.userService.data.Good;
import ua.com.polyanski.userService.data.Goods;

public class CashRegisterController {

    private ObservableList<Good> goodData = FXCollections.observableArrayList();

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
    TextField barCodeField;
    @FXML
    TextField numberField;


    @FXML
    private void initialize() {
//        initData();
//
//        barCodeColum.setCellValueFactory(new PropertyValueFactory<Good,String>("barcode"));
//        typeColum.setCellValueFactory(new PropertyValueFactory<Good,String>("type"));
//        nameColum.setCellValueFactory(new PropertyValueFactory<Good,String>("name"));
//        modelColum.setCellValueFactory(new PropertyValueFactory<Good,String>("model"));
//        numberColum.setCellValueFactory(new PropertyValueFactory<Good,String>("number"));
//        priceColum.setCellValueFactory(new PropertyValueFactory<Good,Double>("price"));
//
//        dataTable.setItems(goodData);
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

    public void add() {
        System.out.println("add");
    }

    public void clear(){
        System.out.println("clear");
    }

    public void delete() {
        System.out.println("delete");
    }

    public void buy() {
        System.out.println("buy");
    }
}
