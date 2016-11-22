package ua.com.polyanski.DBService;

import ua.com.polyanski.userService.data.Seller;
import ua.com.polyanski.userService.data.Sellers;

import java.sql.*;

/**
 * Created by vadym on 16.11.2016.
 */
public class ConnectSellersDB extends ConnectDB {
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;
    private final String URL = "jdbc:sqlite:Supermarket.db";
    private String sql;
    String toSearchLogin = "";

    public void setToSearchLogin(String toSearchLogin) {
        this.toSearchLogin = "and login = '" +toSearchLogin + "'";
    }

    public Sellers select() {
        Sellers sellers = new Sellers();

        try {
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(URL);

            sql = "select\n" +
                    "id, nameSeller, surname, birthday,login,password\n" +
                    "from Seller WHERE flag = 0 " + toSearchLogin;

            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while (res.next()) {
                Seller seller = new Seller(res.getInt("id"), res.getString("nameSeller"), res.getString("surname"),
                        res.getString("birthday"), res.getString("login"), res.getString("password"), 0);
                sellers.add(seller);
            }
            return sellers;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{if(conn!=null)conn.close();} catch (SQLException e) {e.printStackTrace();}
//            try{if(stmt!=null)stmt.close();} catch (SQLException e) {e.printStackTrace();}
            try{if(res!= null) res.close();} catch (SQLException e) {e.printStackTrace();}
        }
        return null;
    }

    public void insert(Seller seller) {
        sql = "insert into Seller (nameSeller, surname, birthday, login, password) \n" +
                "        values ('"+ seller.getName() +"', '"+ seller.getSurname()+ "', '" +
                seller.getBirthday() + "', '"+ seller.getLogin() +"', '" + seller.getPassword() + "')";

        changesTable(sql);
    }

    public void update(Seller seller) {
        sql = "update Seller\n" +
                "set nameSeller = '"+ seller.getName() + "',\n" +
                "surname = '"+ seller.getSurname() +"',\n" +
                "birthday = '"+ seller.getBirthday() +"',\n" +
                "login = '" + seller.getLogin() +"',\n" +
                "password = '" + seller.getPassword() + "' where id = "+ seller.getId();

        changesTable(sql);
    }

    public void delete(int id) {
        sql = "update Seller\n" +
                "set flag = 1 where id = " + id;

        changesTable(sql);
    }
}
