package ua.com.polyanski.userService;

import ua.com.polyanski.userService.data.Goods;

/**
 * Created by vadym on 10.11.2016.
 */
public interface CashRegister  {
    public double bill(Goods goods);
    public boolean isPaid();
}
