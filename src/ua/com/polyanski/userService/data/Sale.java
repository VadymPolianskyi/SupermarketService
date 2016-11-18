package ua.com.polyanski.userService.data;

/**
 * Created by vadym on 18.11.2016.
 */
public class Sale {
    private String sellerName;
    private String sellerSurname;
    private String good;
    private String date;
    private double price;
    private int count;

    public Sale(String sellerName, String sellerSurname, String good, double price, String date, int count) {
        this.sellerName = sellerName;
        this.sellerSurname = sellerSurname;
        this.good = good;
        this.price = price;
        this.date = date;
        this.count = count;
    }

    public String getSellerName() {
        return sellerName;
    }

    public double getPrice() {
        return price;
    }

    public String getSellerSurname() {
        return sellerSurname;
    }

    public String getGood() {
        return good;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }
}
