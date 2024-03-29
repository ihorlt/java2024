package ua.edu.nung.pz.dao.entity;

import java.util.Arrays;

public class Cart {
    private User user;
    private Good[] goods;

    public Cart() {
    }

    public Cart(User user, Good[] goods) {
        this.user = user;
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Good[] getGoods() {
        return goods;
    }

    public void setGoods(Good[] goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "user=" + user +
                ", goods=" + Arrays.toString(goods) +
                '}';
    }
}
