package ua.com.polyanski.userService;

import ua.com.polyanski.DBService.ConnectAnotherDB;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by vadym on 13.11.2016.
 */
public class PasswordService {

    ConnectAnotherDB sellerDB;

    public boolean checkPassword(String login,String password) {
            sellerDB = new ConnectAnotherDB("Seller", "password", login);
        ArrayList<String> passwordFromDB = sellerDB.select();
            if ((passwordFromDB.isEmpty()) && passwordFromDB.equals(password)) {
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
        return password.equals("admin");
    }
}
