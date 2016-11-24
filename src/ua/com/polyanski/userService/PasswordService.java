package ua.com.polyanski.userService;

import ua.com.polyanski.DBService.ConnectAdminDB;
import ua.com.polyanski.DBService.ConnectAnotherDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vadym on 13.11.2016.
 */
public class PasswordService {

    ConnectAnotherDB sellerDB;

    public boolean checkPassword(String login,String password) {
            sellerDB = new ConnectAnotherDB("Seller", "password", login);
        System.out.println("here");
        ArrayList<String> passwordFromDB = sellerDB.select();
        System.out.println(passwordFromDB);
        System.out.println(password);
            if (!(passwordFromDB.isEmpty()) && passwordFromDB.get(0).equals(password)) {
                return true;
            } else {
                return false;
            }
    }

    public void setNewPassword(String login, String password) {
        sellerDB = new ConnectAnotherDB("Seller", "password", login);
        sellerDB.updatePassword(password, login);
    }

    public boolean checkAdminPassword(String password) {
        ConnectAdminDB connectAdminDB = new ConnectAdminDB();
        if (password.equals(connectAdminDB.select().get(0)) || password.equals(connectAdminDB.select().get(1))){
            return true;
        } else {
            return false;
        }
    }
}
