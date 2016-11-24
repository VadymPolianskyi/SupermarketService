package ua.com.polyanski.DBService;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by vadym on 09.11.2016.
 */
public abstract class ConnectDB {
    Connection conn = null;
    Statement stmt = null;
    private final String URL = "jdbc:sqlite:Supermarket.db";



    public void changesTable(String sql) {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();

            conn = DriverManager.getConnection(URL);
            stmt = conn.createStatement();

            stmt.executeUpdate(sql);


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
        }
    }
}
