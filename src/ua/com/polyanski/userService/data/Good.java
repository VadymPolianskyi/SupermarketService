package ua.com.polyanski.userService.data;

/**
 * Created by vadym on 09.11.2016.
 */
public class Good {
    private String barcode;
    private int id;
    private String type;
    private String name;
    private String model;
    private String expiration_date;
    private double price;
    private int number;
    private int sale;
    private int flag;

    private String value;


    public String getBarcode() {
        return barcode;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public int getSale() {
        return sale;
    }

    public int getFlag() {
        return flag;
    }

    public Good(String barcode, int id, String type, String name, String model,
                String expiration_date, double price, int number, int sale, int flag) {
        this.barcode = barcode;
        this.id = id;
        this.type = type;
        this.name = name;
        this.model = model;
        this.expiration_date = expiration_date;
        this.price = price;
        this.number = number;
        this.sale = sale;
        this.flag = flag;
    }

    public Good(String value) {
        this.value = value;
    }

    public void salling(int index) {
        this.number = number - index;
    }


}
