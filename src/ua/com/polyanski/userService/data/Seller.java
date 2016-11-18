package ua.com.polyanski.userService.data;

import ua.com.polyanski.DBService.ConnectSalesDB;

/**
 * Created by vadym on 15.11.2016.
 */
public class Seller {
    private int id;
    private String name;
    private String surname;
    private String birthday;
    private String login;
    private String password;
    private double sales;
    private  int flag;


    public Seller(int id, String name, String surname, String birthday, String login, String password, int flag) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.sales = searchSalesToMonth();
        this.flag = flag;
    }

    public double searchSalesToMonth() {
        ConnectSalesDB connectSalesDB = new ConnectSalesDB();
        return connectSalesDB.salToMon(this.id);
    }



    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSales() {
        return sales;
    }

}
