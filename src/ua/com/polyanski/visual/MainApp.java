package ua.com.polyanski.visual;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private Stage anotherStage;

    private String message;
    private String sellerName;
    private String sellerSurname;
    private double price;

    @FXML
    Button closeButton;

    public void setNameSurnameSeller(String sellerName, String sellerSurname) {
        this.sellerName = sellerName;
        this.sellerSurname = sellerSurname;
    }

    public void setCloseButton(Button closeButton) {
        this.closeButton = closeButton;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setPrice(double price) {
        System.out.println("Main set: " + price);
        this.price = price;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root =(Parent) fxmlLoader.load(getClass().getResourceAsStream("../visual/resources/login.fxml"));
        this.primaryStage.setTitle("Sig in");
        this.primaryStage.setResizable(false);
        this.primaryStage.getIcons().add(new Image("pictures/title.png"));
        this.primaryStage.setScene(new Scene(root));
        LoginController loginController = fxmlLoader.getController();
        loginController.setMain(this);
        this.primaryStage.show();
    }

    public void showWindow(String programName, String title) {

        try {
            FXMLLoader loader  = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream("../visual/resources/" + programName));

            anotherStage = new Stage();
            anotherStage.setTitle(title);
            anotherStage.setResizable(false);
            anotherStage.getIcons().add(new Image("pictures/title.png"));
//            anotherStage.initModality(Modality.WINDOW_MODAL);
            anotherStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            anotherStage.setScene(scene);

            switch (programName) {
                case "addGoodsAndSellers.fxml" :
                    AddGoodsAndSellersController addGoodsAndSellersController = loader.getController();
                    addGoodsAndSellersController.setMain(this);
                    break;
                case "addTypeNameModel.fxml" :
                    AddTypeNameModelController addTypeNameModelController = loader.getController();
                    addTypeNameModelController.setMain(this);
                    break;
                case "adminPass.fxml" :
                    AdminPassController adminPassController = loader.getController();
                    adminPassController.setMain(this);
                    break;
                case "cashRegister.fxml" :
                    CashRegisterController cashRegisterController = loader.getController();
                    cashRegisterController.setNameSurnameSeller(sellerName, sellerSurname);
                    cashRegisterController.setMain(this);
                    break;
                case "inform.fxml" :
                    InformController informController = loader.getController();
                    informController.setMessage(message);
                    informController.setCloseButton(closeButton);
                    break;
                case "informSecond.fxml" :
                    InformSecondController informSecondController = loader.getController();
                    informSecondController.setMessage(message);
                    break;
                case "login.fxml" :
                    LoginController loginController = loader.getController();
                    loginController.setMain(this);
                    break;
                case "registration.fxml" :
                    RegistrationController registrationController = loader.getController();
                    registrationController.setMain(this);
                    break;
                case "tableSales.fxml" :
                    TableSalesController tableSalesController = loader.getController();
                    tableSalesController.setMain(this);
                    break;
                case "remainder.fxml" :
                    RemainderController remainderController = loader.getController();
                    remainderController.setPrice(price);
                    break;

            }

            anotherStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void informationDilogYesNo(Button closeButton, String message) {
        this.message = message;
        this.closeButton = closeButton;
        showWindow("inform.fxml", "Information Dialog");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
