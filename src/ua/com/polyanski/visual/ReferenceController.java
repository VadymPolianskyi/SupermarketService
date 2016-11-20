package ua.com.polyanski.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Created by vadym on 19.11.2016.
 */
public class ReferenceController {

    @FXML
    TreeView treeView;

    @FXML
    Label firstStep;
    @FXML
    Label secondStep;
    @FXML
    Label thirdStep;

    @FXML
    public void initialize() {
        TreeItem<String> help = new TreeItem<>("Help");
        TreeItem<String> Administrator = new TreeItem<>("Administrator");
        TreeItem<String> Seller = new TreeItem<>("Seller");
        TreeItem<String> Language = new TreeItem<>("Change language");

        TreeItem<String> addingToAdmin = new TreeItem<>("Add data");
        TreeItem<String> addingSmallDataToAdmin = new TreeItem<>("Add type/name/model");
        TreeItem<String> changeDataAdmin = new TreeItem<>("Change good's/seller's data");
        TreeItem<String> changePassword = new TreeItem<>("Change seller's password");
        TreeItem<String> tableSales = new TreeItem<>("Viev table sales");

        TreeItem<String> sale = new TreeItem<>("Sale good");
        TreeItem<String> add = new TreeItem<>("Add good");
        TreeItem<String> delete = new TreeItem<>("Delete goog from list");
        TreeItem<String> changeNumber = new TreeItem<>("Change number");
        TreeItem<String> clearList = new TreeItem<>("Clear good's list");


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
        switch (value){
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

    public void language() {
        firstStep.setText("Choose menu item 'Language'.");
        secondStep.setText("Change the language on the right.");
        thirdStep.setText("Only 'Ukraine', 'Russian', 'English'.");
    }

    public void addData() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void addSmallData() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void changeData() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void changePassword() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void viewSales() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void saleGoods() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void addGoods() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void deleteGoods() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void changeNumberGood() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }

    public void clearListGoods() {
        firstStep.setText("");
        secondStep.setText("");
        thirdStep.setText("");
    }


}
