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
        firstStep.setText("Choose menu item 'Language'.");
        secondStep.setText("Change the language on the right.");
        thirdStep.setText("Only 'Ukraine', 'Russian', 'English'.");
    }

    public void addData() {
        firstStep.setText("You can select area by pressing  \nthe top tab(good/seller)");
        secondStep.setText("Enter information in the all fields(good/seller).\n" +
                "If you want to add seller enter password in \nfirst field 'New password'.");
        thirdStep.setText("Click 'Add' when finished");
    }

    public void addSmallData() {
        firstStep.setText("Click 'File' and then 'Add type/name/model' \nin top menu.");
        secondStep.setText("Enter type/name/model in the field.");
        thirdStep.setText("Click the appropriate button.");
    }

    public void changeData() {
        firstStep.setText("Choose the item(good/seller) from the table.");
        secondStep.setText("Change data in the fields(without password).");
        thirdStep.setText("Click 'Change'.");
    }

    public void changePassword() {
        firstStep.setText("Choose the item(seller) from the table.");
        secondStep.setText("Enter old password and new password(twice).");
        thirdStep.setText("Click 'Change password'.");
    }

    public void viewSales() {
        firstStep.setText("Choose menu item 'File'.");
        secondStep.setText("Then choose 'Table sales'.");
        thirdStep.setText("If you want to close, just click 'Close'.");
    }

    public void saleGoods() {
        firstStep.setText("Enter the correct bar code in the appropriate field.");
        secondStep.setText("Add the right products(click button 'add').");
        thirdStep.setText("Click button 'Buy'.");
    }

    public void addGoods() {
        firstStep.setText("Enter the correct bar code in the appropriate \nfield.");
        secondStep.setText("Enter number if you want more goods then one.");
        thirdStep.setText("Click button 'Add'.");
    }

    public void deleteGoods() {
        firstStep.setText("Choose good in the table.");
        secondStep.setText("Click button 'Delete'.");
        thirdStep.setText("That is all.");
    }

    public void changeNumberGood() {
        firstStep.setText("Select good, delete it.");
        secondStep.setText("Enter the right bar code. \nEnter the right number.");
        thirdStep.setText("Click 'Add'.");
    }

    public void clearListGoods() {
        firstStep.setText("Just click button 'Clear'.");
        secondStep.setText("Your table is empty...");
        thirdStep.setText("");
    }


}
