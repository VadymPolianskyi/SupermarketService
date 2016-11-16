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
    private String toPassword = "";

    public ConnectAnotherDB(String tableName, String column) {
        this.tableName = tableName;
        this.column = column;
    }

    public ConnectAnotherDB(String tableName, String column, String login) {
        this.tableName = tableName;
        this.column = column;
        toPassword = "where login = " + login;
    }

    public ArrayList select() {
        ArrayList<String> data = new ArrayList<String>();
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(URL);

            sql = "    select " + column +
                    "    from " + tableName + toPassword;

            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while(res.next()) {
                data.add(res.getString(column));
            }
            return data;

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

//    public void insert(String value) {
//        String sql =  "insert into " + tableName + "("+column+")" +
//                "value(" + value +")";
//        changesTable(sql);
//    }

    public void updatePassword(String password, String login) {
        String sql =  "update Seller \n" +
                "set password = " + password + "where  login = " + login;
        changesTable(sql);
    }

    public void insertSaller(String name, String surname, String dataBirthday, String login, String password) {
        String sql =  "insert into Seller (name, surname, birthday, login, password)" +
                "value(" + name +", " + surname + ", " + dataBirthday + ", " + login +", " + password +")";
        changesTable(sql);
    }

    public void insertSale(String seller, String good, String date) {

        String sql = "insert into Sale (saller_id, good_id, date_sale, d)" +
                "select id, (select id from Seller where name = '" + seller + "'),"+
                "(select id from Good where name = '" + good +"')," + date +
                "from Seller where name = '" + seller + "'";

        changesTable(sql);
    }
}
