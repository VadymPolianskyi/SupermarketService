package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by vadym on 19.11.2016.
 */
public class ReferenceController implements Initializable{

    @FXML
    TreeView treeView;

    @FXML
    Label firstStep;
    @FXML
    Label secondStep;
    @FXML
    Label thirdStep;

    ResourceBundle resourceBundle;



    @FXML
    public void initialize() {
        TreeItem<String> help = new TreeItem<>(resourceBundle.getString("help"));
        TreeItem<String> Administrator = new TreeItem<>(resourceBundle.getString("admin"));
        TreeItem<String> Seller = new TreeItem<>(resourceBundle.getString("seller_simple"));
        TreeItem<String> Language = new TreeItem<>(resourceBundle.getString("change_language"));

        TreeItem<String> addingToAdmin = new TreeItem<>(resourceBundle.getString("add_data"));
        TreeItem<String> addingSmallDataToAdmin = new TreeItem<>(resourceBundle.getString("add_type_name_model"));
        TreeItem<String> changeDataAdmin = new TreeItem<>(resourceBundle.getString("change_good_seller_data"));
        TreeItem<String> changePassword = new TreeItem<>(resourceBundle.getString("change_sellers_password"));
        TreeItem<String> tableSales = new TreeItem<>(resourceBundle.getString("view_table_sales"));

        TreeItem<String> sale = new TreeItem<>(resourceBundle.getString("sale_good"));
        TreeItem<String> add = new TreeItem<>(resourceBundle.getString("add_good"));
        TreeItem<String> delete = new TreeItem<>(resourceBundle.getString("delete_from_list"));
        TreeItem<String> changeNumber = new TreeItem<>(resourceBundle.getString("change_number"));
        TreeItem<String> clearList = new TreeItem<>(resourceBundle.getString("clear_list"));


        Administrator.setExpanded(false);
        Seller.setExpanded(false);

        help.getChildren().addAll(Administrator, Seller, Language);
        Administrator.getChildren().addAll(addingToAdmin, addingSmallDataToAdmin, changeDataAdmin, changePassword,tableSales);
        Seller.getChildren().addAll(sale, add,delete,changeNumber, clearList);
        treeView.setRoot(help);
        treeView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> getAction(observable.getValue().toString().substring(18)));
    }

    public void getAction(String value){
        ukrAction(value);
        enAction(value);
        rusAction(value);
    }

    public void ukrAction(String value) {
        switch (value){
            case "Допомога ]" :
                empty();
                break;
            case "Адміністратор ]" :
                empty();
                break;
            case "Продавець ]" :
                empty();
                break;
            case "Змінити мову ]" :
                language();
                break;
            case "Додавання даних ]" :
                addData();
                break;
            case "Додати тип/назву/модель ]" :
                addSmallData();
                break;
            case "Змінити характеристики товарів/продавців ]" :
                changeData();
                break;
            case "Змінити пароль продавця ]" :
                changePassword();
                break;
            case "Дивитись таблицю продажів ]" :
                viewSales();
                break;
            case "Продаж товару ]" :
                saleGoods();
                break;
            case "Додавання товару ]" :
                addGoods();
                break;
            case "Видалити товар зі списку ]" :
                deleteGoods();
                break;
            case "Змінити кількість ]" :
                changeNumberGood();
                break;
            case "Очистити список товарів ]" :
                clearListGoods();
                break;
        }
    }

    public void rusAction(String value) {
        switch (value){
            case "Помощ ]" :
                empty();
                break;
            case "Администратор ]" :
                empty();
                break;
            case "Продавец ]" :
                empty();
                break;
            case "Изменить язык ]" :
                language();
                break;
            case "Добавление даных ]" :
                addData();
                break;
            case "Добавить тип/название/модель ]" :
                addSmallData();
                break;
            case "Изменить характеристики товаров/продавцов ]" :
                changeData();
                break;
            case "Изменить пароль продавца ]" :
                changePassword();
                break;
            case "Смотреть таблицу продаж ]" :
                viewSales();
                break;
            case "Продажа товара ]" :
                saleGoods();
                break;
            case "Добавление товара ]" :
                addGoods();
                break;
            case "Удалить товар из списка ]" :
                deleteGoods();
                break;
            case "Изменить количество ]" :
                changeNumberGood();
                break;
            case "Очистить список товаров ]" :
                clearListGoods();
                break;
        }
    }

    public void enAction(String value) {
        switch (value){
            case "Help ]" :
                empty();
                break;
            case "Administrator ]" :
                empty();
                break;
            case "Seller ]" :
                empty();
                break;
            case "Change language ]" :
                language();
                break;
            case "Add data ]" :
                addData();
                break;
            case "Add type/name/model ]" :
                addSmallData();
                break;
            case "Change good's/seller's data ]" :
                changeData();
                break;
            case "Change seller's password ]" :
                changePassword();
                break;
            case "Viev table sales ]" :
                viewSales();
                break;
            case "Sale goods ]" :
                saleGoods();
                break;
            case "Add good ]" :
                addGoods();
                break;
            case "Delete goog from list ]" :
                deleteGoods();
                break;
            case "Change number ]" :
                changeNumberGood();
                break;
            case "Clear good's list ]" :
                clearListGoods();
                break;
        }
    }

    public void empty() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void language() {
        firstStep.setText(resourceBundle.getString("choose_language_help_first"));
        secondStep.setText(resourceBundle.getString("choose_language_help_second"));
        thirdStep.setText(resourceBundle.getString("choose_language_help_third"));
    }

    public void addData() {
        firstStep.setText(resourceBundle.getString("add_data_admin_first"));
        secondStep.setText(resourceBundle.getString("add_data_admin_second"));
        thirdStep.setText(resourceBundle.getString("add_data_admin_third"));
    }

    public void addSmallData() {
        firstStep.setText(resourceBundle.getString("add_small_data_admin_first"));
        secondStep.setText(resourceBundle.getString("add_small_data_admin_second"));
        thirdStep.setText(resourceBundle.getString("add_small_data_admin_third"));
    }

    public void changeData() {
        firstStep.setText(resourceBundle.getString("change_data_admin_help_first"));
        secondStep.setText(resourceBundle.getString("change_data_admin_help_second"));
        thirdStep.setText(resourceBundle.getString("change_data_admin_help_third"));
    }

    public void changePassword() {
        firstStep.setText(resourceBundle.getString("change_password_admin_first"));
        secondStep.setText(resourceBundle.getString("change_password_admin_second"));
        thirdStep.setText(resourceBundle.getString("change_password_admin_third"));
    }

    public void viewSales() {
        firstStep.setText(resourceBundle.getString("view_sales_admin_help_first"));
        secondStep.setText(resourceBundle.getString("view_sales_admin_help_second"));
        thirdStep.setText(resourceBundle.getString("view_sales_admin_help_third"));
    }

    public void saleGoods() {
        firstStep.setText(resourceBundle.getString("sale_goods_seller_help_first"));
        secondStep.setText(resourceBundle.getString("sale_goods_seller_help_second"));
        thirdStep.setText(resourceBundle.getString("sale_goods_seller_help_third"));
    }

    public void addGoods() {
        firstStep.setText(resourceBundle.getString("add_goods_seller_help_first"));
        secondStep.setText(resourceBundle.getString("add_goods_seller_help_second"));
        thirdStep.setText(resourceBundle.getString("add_goods_seller_help_third"));
    }

    public void deleteGoods() {
        firstStep.setText(resourceBundle.getString("delete_goods_seller_help_first"));
        secondStep.setText(resourceBundle.getString("delete_goods_seller_help_second"));
        thirdStep.setText(resourceBundle.getString("delete_goods_seller_help_third"));
    }

    public void changeNumberGood() {
        firstStep.setText(resourceBundle.getString("change_number_seller_help_first"));
        secondStep.setText(resourceBundle.getString("change_number_seller_help_second"));
        thirdStep.setText(resourceBundle.getString("change_number_seller_help_third"));
    }

    public void clearListGoods() {
        firstStep.setText(resourceBundle.getString("clear_list_seller_help_first"));
        secondStep.setText(resourceBundle.getString("clear_list_seller_help_second"));
        thirdStep.setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        initialize();
    }
}
