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

    public Seller select() {
        Sellers sellers = new Sellers();

        try {
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(URL);

            sql = "";

            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while (res.next()) {
                Seller seller = new Seller();
                seller.setId(res.getInt("id"));
                seller.setName(res.getString("name"));
                seller.setSurname(res.getString("surname"));
                seller.setBirthday(res.getString("birthday"));
                seller.setLogin(res.getString("login"));
                seller.setPassword(res.getString("password"));
                sellers.add(seller);
            }
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
}
