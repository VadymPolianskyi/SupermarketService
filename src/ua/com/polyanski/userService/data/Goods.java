package ua.com.polyanski.userService.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadym on 09.11.2016.
 */
public class Goods{
    List<Good> goods;

    public Goods() {
        this.goods =  new ArrayList<Good>();
    }

    public void add(Good good) {
        goods.add(good);
    }

    public Good get(int index) {
        return goods.get(index);
    }

    public void remove(int index) {
        goods.remove(index);
    }

    public int size() {
        return goods.size();
    }
}
