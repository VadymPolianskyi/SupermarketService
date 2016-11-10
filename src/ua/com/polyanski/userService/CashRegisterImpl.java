package ua.com.polyanski.userService;

import ua.com.polyanski.DBService.ConnectMainDB;
import ua.com.polyanski.userService.data.Good;
import ua.com.polyanski.userService.data.Goods;

/**
 * Created by vadym on 10.11.2016.
 */
public class CashRegisterImpl implements CashRegister{
    ConnectMainDB connectMainDB = new ConnectMainDB();

    boolean paid = false;

    public double purchasePrice(Good good) {
        int sale = good.getSale();
        double price = good.getPrice();
        if (sale != 0) {
            return price * sale /100;
        }

        return price;
    }

    @Override
    public double build(Goods goods, int number) {
        double price = 0;
        for (int i = 0; i < goods.size(); i++) {
            price += purchasePrice(goods.get(i));
        }

        return price;
    }

    @Override
    public void paid() {
        paid = true;
    }

    @Override
    public boolean isPaid() {
        return paid;
    }
}
