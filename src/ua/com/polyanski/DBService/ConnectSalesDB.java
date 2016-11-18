package ua.com.polyanski.DBService;

import ua.com.polyanski.userService.data.Seller;
import ua.com.polyanski.userService.data.Sellers;

import java.sql.*;

/**
 * Created by vadym on 18.11.2016.
 */
public class ConnectSalesDB extends ConnectDB {
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;
    private final String URL = "jdbc:sqlite:Supermarket.db";
    private String sql;

    public Sellers select(int id) {
        Sellers sellers = new Sellers();

        try {
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(URL);

            sql = "select\n" +
                    "good_id, date_sale, count_goods\n" +
                    "from Sale where saller_id = " + id;

            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while (res.next()) {

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
}
