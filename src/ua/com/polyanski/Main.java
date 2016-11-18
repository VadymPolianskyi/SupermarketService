package ua.com.polyanski;


import ua.com.polyanski.DBService.ConnectGoodsDB;
import ua.com.polyanski.userService.data.Good;
import ua.com.polyanski.userService.data.Goods;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {




    public static void main(String[] args) {
        ConnectGoodsDB connect = new ConnectGoodsDB();

        Goods goods = connect.select();
        Good good = goods.get(0);
        System.out.println("name " + good.getName());
        System.out.println("id " + good.getId());
        System.out.println("model " + good.getModel());
        System.out.println("type " + good.getType());


    }
}
