package ua.com.polyanski.userService.data;

import java.util.ArrayList;

/**
 * Created by vadym on 18.11.2016.
 */
public class Sales {
    ArrayList<Sale> sales = new ArrayList<Sale>();

    public Sales () {
        this.sales = new ArrayList<Sale>();
    }

    public void add(Sale sale) {
        sales.add(sale);
    }

    public int size() {
        return sales.size();
    }

    public void remove(int index) {
        sales.remove(index);
    }

    public Sale get(int index) {
        return sales.get(index);
    }
}
