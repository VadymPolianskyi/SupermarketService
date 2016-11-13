package ua.com.polyanski.DBService;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by vadym on 09.11.2016.
 */
public class ConnectChangeDB extends ConnectDB{
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;
    private final String URL = "jdbc:sqlite:Supermarket.db";
    private String sql;
    private String tableName;
    private String column;

    public ConnectChangeDB(String tableName, String column) {
        this.tableName = tableName;
        this.column = column;
    }

    public ArrayList select() {
        ArrayList<String> types = null;
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(URL);

            sql = "    select name" +
                    "    from " + tableName;
            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while(res.next()) {
                types.add(res.getString("name" + column));
            }
            return types;

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
            try{if(stmt!=null)stmt.close();} catch (SQLException e) {e.printStackTrace();}
            try{if(res!= null) res.close();} catch (SQLException e) {e.printStackTrace();}

            return null;
        }
    }

    public void insert(String value) {
        String sql =  "insert into " + tableName + "(name)" +
                "value(";
        changesTable(sql);
    }

    public void update(String value, int id) {
        String sql =  "update "+ tableName+ "\n" +
                "set name = " + value + "where id = " + id;
        changesTable(sql);
    }
}
