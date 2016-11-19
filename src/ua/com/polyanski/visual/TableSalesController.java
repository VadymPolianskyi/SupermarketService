package ua.com.polyanski.visual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.com.polyanski.DBService.ConnectSalesDB;
import ua.com.polyanski.userService.data.Sale;
import ua.com.polyanski.userService.data.Sales;

/**
 * Created by vadym on 18.11.2016.
 */
public class TableSalesController {
    private ObservableList<Sale> saleData = FXCollections.observableArrayList();
    @FXML
    TableView<Sale> tableViewSales;
    @FXML
    TableColumn<Sale, String> sellersNameColumn;
    @FXML
    TableColumn<Sale, String> sellersSurnameColumn;
    @FXML
    TableColumn<Sale, String> goodColumn;
    @FXML
    TableColumn<Sale, String> dateColumn;
    @FXML
    TableColumn<Sale, Double> priceColumn;
    @FXML
    TableColumn<Sale, Integer> numberColumn;

    @FXML
    public void initialize() {
    dataInit();
        sellersNameColumn.setCellValueFactory(new PropertyValueFactory<Sale,String>("sellerName"));
        sellersSurnameColumn.setCellValueFactory(new PropertyValueFactory<Sale,String>("sellerSurname"));
        goodColumn.setCellValueFactory(new PropertyValueFactory<Sale,String>("good"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Sale,String>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Sale,Double>("price"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("count"));

        tableViewSales.setItems(saleData);
    }

    public void dataInit() {
        ConnectSalesDB connectSalesDB = new ConnectSalesDB();
        Sales sales = connectSalesDB.select();

        for (int i = 0; i < sales.size(); i++) {
            saleData.add(sales.get(i));
        }
    }

    public void cancelClick() {
        System.out.println("Cancel");
    }
}
