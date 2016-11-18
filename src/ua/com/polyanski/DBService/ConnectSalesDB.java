package ua.com.polyanski.DBService;

import ua.com.polyanski.userService.data.Sale;
import ua.com.polyanski.userService.data.Sales;
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

    public Sales select(int id) {
       Sales sales = new Sales();

        try {
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(URL);

            sql = "select\n" +
                    "seller.nameSeller,\n" +
                    "seller.surname,\n" +
                    "name.name,\n" +
                    "good.price, \n" +
                    "sale.date_sale, \n" +
                    "sale.count_goods\n" +
                    "from Sale sale\n" +
                    "inner join Good good on sale.good_id = good.id\n" +
                    "inner join spr_Name name on good.name = name.id\n" +
                    "inner join Seller seller on sale.seller_id = seller.id\n" +
                    "where seller_id = " + id;

            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while (res.next()) {
                sales.add(new Sale(res.getString("nameSeller"), res.getString("surname"),
                        res.getString("name"), res.getDouble("price"), res.getString("date_sale"),
                            res.getInt("count_goods")));
            }
            return sales;
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

    //calculate ~ sales/month
    public double salToMon(int id) {
        Sales sales = select(id);
        int count = 0;
        double allResults = 0;
        double monthResult = 0;
        String dateToRemember = null;
        boolean flag = false;
        Sale sale;
        for (int i = 0; i < sales.size(); i++) {
            sale = sales.get(i);
           String date = sale.getDate();
            double price = sale.getPrice();
            int number = sale.getCount();

            if(dateToRemember == null) {
                dateToRemember = date;
                monthResult += price * number;
                count ++;
            } else if (dateToRemember.substring(0,7).equals(date.substring(0,7))){
                monthResult += price * number;
                monthResult = monthResult/2;
                flag = false;
            } else {
                allResults += monthResult;
                count ++;
                dateToRemember = date;
                monthResult = price;
                flag = true;
            }
        }
        if (flag) {
            count ++;
        }
        allResults += monthResult;

        return allResults / count;
    }
}
