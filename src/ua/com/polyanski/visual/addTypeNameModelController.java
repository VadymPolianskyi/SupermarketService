package ua.com.polyanski.visual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.com.polyanski.DBService.ConnectAnotherDB;
import ua.com.polyanski.userService.smallDetails.*;

import java.util.ArrayList;

/**
 * Created by vadym on 19.11.2016.
 */
public class addTypeNameModelController {
    private ObservableList<Type> typeData = FXCollections.observableArrayList();
    private ObservableList<Name> nameData = FXCollections.observableArrayList();
    private ObservableList<Model> modelData = FXCollections.observableArrayList();
    ConnectAnotherDB connectAnotherDB;

    Type selectType;
    Name selectName;
    Model selectModel;

    @FXML
    TableView<Type> typeTable;
    @FXML
    TableColumn<Type, String> typeColumn;
    @FXML
    TextField typeField;
    @FXML
    Button addTypeButton;

    @FXML
    TableView<Name> nameTable;
    @FXML
    TableColumn<Name, String> nameColumn;
    @FXML
    TextField nameField;
    @FXML
    Button addNameButton;

    @FXML
    TableView<Model> modelTable;
    @FXML
    TableColumn<Model, String> modelColumn;
    @FXML
    TextField modelField;
    @FXML
    Button addModelButton;

    @FXML
    public void initialize() {
        typeData.clear();
        nameData.clear();
        modelData.clear();

        dataInit();
        typeColumn.setCellValueFactory(new PropertyValueFactory<Type,String>("type"));
        typeTable.setItems(typeData);

        nameColumn.setCellValueFactory(new PropertyValueFactory<Name,String>("name"));
        nameTable.setItems(nameData);

        modelColumn.setCellValueFactory(new PropertyValueFactory<Model,String>("model"));
        modelTable.setItems(modelData);

        typeTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showTypeSelectedData(newValue)));
        nameTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showNameSelectedData(newValue)));
        modelTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showModelSelectedData(newValue)));
    }

    private void showModelSelectedData(Model selectModel) {
        this.selectModel = selectModel;
        modelField.setText(selectModel.getModel());

    }

    private void showNameSelectedData(Name selectName) {
        this.selectName = selectName;
        nameField.setText(selectName.getName());

    }

    private void showTypeSelectedData(Type selectType) {
        this.selectType = selectType;
        typeField.setText(selectType.getType());
    }

    private void dataInit() {
        connectAnotherDB = new ConnectAnotherDB("spr_Type", "nameType");

        ArrayList<String> typesName = connectAnotherDB.select();
        Types types = new Types();
        types.addAll(typesName);

        for (int i = 0; i < types.size(); i++) {
            typeData.add(types.get(i));
        }

        connectAnotherDB = new ConnectAnotherDB("spr_Name", "name");

        ArrayList<String> namesName = connectAnotherDB.select();
        Names names = new Names();
        names.addAll(namesName);

        for (int i = 0; i < names.size(); i++) {
            nameData.add(names.get(i));
        }


        connectAnotherDB = new ConnectAnotherDB("spr_Model", "nameModel");

        ArrayList<String> modelsName = connectAnotherDB.select();
        Models models = new Models();
        models.addAll(modelsName);

        for (int i = 0; i < models.size(); i++) {
            modelData.add(models.get(i));
        }
    }

    public void typeChangeClick() {
        if (typeField.getText() != null) {
            connectAnotherDB = new ConnectAnotherDB("spr_Type", "nameType");

            connectAnotherDB.update(typeField.getText(), selectType.getType());
            initialize();
        }
    }

    public void nameChangeClick() {
        if (nameField != null) {
            connectAnotherDB = new ConnectAnotherDB("spr_Name", "name");

            connectAnotherDB.update(nameField.getText(), selectName.getName());
            initialize();
        }
    }

    public void modelChangeClick() {
        if (modelField != null) {
            connectAnotherDB = new ConnectAnotherDB("spr_Model", "nameModel");

            connectAnotherDB.update(modelField.getText(), selectModel.getModel());
            initialize();
        }
    }


    public void typeAddClick() {
        if (typeField.getText() != null) {
            connectAnotherDB = new ConnectAnotherDB("spr_Type", "nameType");

            connectAnotherDB.insert(typeField.getText());
            initialize();
        }
    }

    public void nameAddClick() {
        if (nameField != null) {
            connectAnotherDB = new ConnectAnotherDB("spr_Name", "name");

            connectAnotherDB.insert(nameField.getText());
            initialize();
        }
    }

    public void modelAddClick() {
        if (modelField != null) {
            connectAnotherDB = new ConnectAnotherDB("spr_Model", "nameModel");

            connectAnotherDB.insert(modelField.getText());
            initialize();
        }
    }

    public void cancelClick() {

    }
}
