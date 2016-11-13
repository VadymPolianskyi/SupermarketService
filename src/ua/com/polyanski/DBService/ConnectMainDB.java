package ua.com.polyanski.DBService;

import ua.com.polyanski.userService.data.Good;
import ua.com.polyanski.userService.data.Goods;

import java.sql.*;

/**
 * Created by vadym on 09.11.2016.
 */
public class ConnectMainDB extends ConnectDB {
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;
    private final String URL = "jdbc:sqlite:Supermarket.db";
    private String sql;




    public Goods select() {
        Goods goods = new Goods();
        try {
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();

            conn = DriverManager.getConnection(URL);

            sql = "    select\n" +
                    "\n" +
                    "    good.barcode,\n" +
                    "    good.id,\n" +
                    "    type.nameType,\n" +
                    "    name.name,\n" +
                    "    model.nameModel,\n" +
                    "    good.expiration_date,\n" +
                    "    good.price,\n" +
                    "    good.number,\n" +
                    "    good.sale,\n" +
                    "    good.flag\n" +
                    "\n" +
                    "    from Good good\n" +
                    "    inner join spr_Type type on good.type = type.id\n" +
                    "    inner join spr_Name name on good.name = name.id\n" +
                    "    inner join spr_Model model on good.model = model.id and flag = 0";
            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);

            while(res.next()) {
                goods.add(new Good(res.getString("barcode"), res.getInt("id"), res.getString("name"),
                        res.getString("nameType"), res.getString("nameModel"), res.getString("expiration_date"),
                            res.getDouble("price"), res.getInt("number"), res.getInt("sale"), res.getInt("flag")));
            }
            return goods;

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
    public void insert(Good good) {

        sql = "insert into Good (type, name, model, expiration_date, price, number, sale, flag, barcode)\n" +
                    "        select id, (select id from spr_Name where name = '" + good.getName() + "'),\n" +
                    "                   (select id from spr_Model where nameModel = '" + good.getModel() + "'),\n" +
                    "                   '" + good.getExpiration_date() + "', " + good.getPrice() + ", " + good.getNumber() +
                    "," + good.getSale() + ", " + good.getFlag() +", "+ good.getBarcode() +
                    " from spr_Type where nameType =  '" + good.getType() + "'";

        changesTable(sql);

    }
    public void update(Good good) {

            sql = "update Good\n" +
                    "    set type = (select id from spr_Type where nameType = '" + good.getType() + "'),\n" +
                    "    name = (select id from spr_Name where name = '" + good.getName() + "'),\n" +
                    "    model = (select id from spr_Model where nameModel = '" + good.getModel() + "'),\n" +
                    "    expiration_date = '" + good.getExpiration_date() + "',\n" +
                    "    price = " + good.getPrice() + ",\n" +
                    "    number = " + good.getNumber() + ",\n" +
                    "    sale = " + good.getSale() + ",\n" +
                    "    flag = " + good.getFlag() + " , \n" +
                    "    barcode = " + good.getBarcode() + " where id = " + good.getId();

        changesTable(sql);

    }

    public void delete(int id) {
        sql = "update Good\n" +
                "set flag = 1 where id = " + id;

        changesTable(sql);
    }


    /*
    insert into Good (type, name, model, expiration_date, price, number)
        select id, (select id from spr_Name where name = 'KitKat'),
                   (select id from spr_Model where name = 'pindra'),
                   '2016-05-04', 30, 5, 0 from spr_Type where name =  'fish'

    --------------------------------------------------------------------------

    select

    type.name,
    name.name,
    model.name,
    good.expiration_date,
    good.price,
    good.number,
    good.sale,
    good.flag

    from Good good
    inner join spr_Type type on good.type = type.id
    inner join spr_Name name on good.name = name.id
    inner join spr_Model model on good.model = model.id and good.id = 11 and flag = 0

    --------------------------------------------------------------------------

    update Good
    set type = (select id from spr_Type where name = 'egg'),
    name = (select id from spr_Name where name = 'Coca-Cola'),
    model = (select id from spr_Model where name = 'white'),
    expiration_date = '2016-11-12',
    price = 31,
    number = 2,
    sale = 30,
    flag = 0 where id = 13
     */
}
