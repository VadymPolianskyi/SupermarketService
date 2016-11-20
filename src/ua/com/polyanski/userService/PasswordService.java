package ua.com.polyanski.userService;

import ua.com.polyanski.DBService.ConnectAnotherDB;

import java.sql.SQLException;

/**
 * Created by vadym on 13.11.2016.
 */
public class PasswordService {

    ConnectAnotherDB sallerDB;

    public boolean checkPassword(String login,String password) {
            sallerDB = new ConnectAnotherDB("Seller", "password", login);
        System.out.println(sallerDB.select().get(0));
        System.out.println(password);
            if (sallerDB.select().get(0).equals(password)) {
                return true;
            } else {
                return false;
            }
    }

    public void setNewPassword(String login, String password) {
        sallerDB = new ConnectAnotherDB("Seller", "password", login);
        sallerDB.updatePassword(password, login);
    }

    public boolean checkAdminPassword(String password) {
        return password.equals("admin");
    }
}
