package ua.com.polyanski.DBService;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by vadym on 24.11.2016.
 */
public class ConnectAdminDB extends ConnectDB{
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;
    private final String URL = "jdbc:sqlite:Supermarket.db";
    private String sql;

    public ArrayList<String> select() {
        ArrayList<String> passwords = new ArrayList<String>();
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(URL);


            sql = "    select  password " +
                    "    from  Admin ";
            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while(res.next()) {
                passwords.add(res.getString("password"));
            }
            return passwords;

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
            try{if(res!= null) res.close();} catch (SQLException e) {e.printStackTrace();}
        }
        return null;
    }

    public void update(String newPassword) {
        sql = "update Admin\n" +
                "set password = '" + newPassword + "' where id = 2";
        changesTable(sql);
    }
}
