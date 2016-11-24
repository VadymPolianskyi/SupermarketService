package ua.com.polyanski.DBService;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by vadym on 09.11.2016.
 */
public class ConnectAnotherDB extends ConnectDB{
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;
    private final String URL = "jdbc:sqlite:Supermarket.db";
    private String sql;
    private String tableName;
    private String column;
    String toPassword = "";

    public ConnectAnotherDB(String tableName, String column) {
        this.tableName = tableName;
        this.column = column;
    }

    public ConnectAnotherDB(String tableName, String column, String login) {
        this.tableName = tableName;
        this.column = column;
        toPassword = "where login = '" + login + "'";
    }


    public ArrayList select() {
        ArrayList<String> data = new ArrayList<String>();
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(URL);


            sql = "    select " + column +
                    "    from " + tableName +" "+ toPassword;
            System.out.println(sql);

            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while(res.next()) {
                data.add(res.getString(column));
            }
            return data;

        } catch (InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQLExeption");
        } finally {
            try{if(conn!=null)conn.close();} catch (SQLException e) {e.printStackTrace();}
//            try{if(stmt!=null)stmt.close();} catch (SQLException e) {e.printStackTrace();}
            try{if(res!= null) res.close();} catch (SQLException e) {e.printStackTrace();}
        }
        return null;
    }



    public void update(String newName, String oldName) {
        sql = "update "+tableName+"\n" +
                "set "+column+" = '"+newName+"' where "+column+" = '"+oldName+"'";
        changesTable(sql);
    }

    public void insert(String name) {
        sql = "insert into " + tableName + "(" + column + ")" +
                "values ('" + name + "')";
        changesTable(sql);
    }

    public void updatePassword(String password, String login) {
        sql = "update Seller " +
                "set password = " + password +
                "where login = " + login;
    }


}
