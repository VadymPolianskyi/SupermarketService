package ua.com.polyanski.userService.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadym on 16.11.2016.
 */
public class Sellers {
    List<Seller> sellers;

    public Sellers () {
        this.sellers = new ArrayList<Seller>();
    }

    public void add(Seller seller) {
        sellers.add(seller);
    }

    public int size() {
        return sellers.size();
    }

    public void remove(int index) {
        sellers.remove(index);
    }

    public Seller get(int index) {
        return sellers.get(index);
    }
}
