package ua.edu.nung.pz.dao.entity;

import java.util.Arrays;

public class Cart {
    private User user;
    private Good[] goods;
    private Order[] orders;

    public Cart() {
    }

    public Cart(User user, Good[] goods, Order[] orders) {
        this.user = user;
        this.goods = goods;
        this.orders = orders;
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

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "user=" + user +
                ", goods=" + Arrays.toString(goods) +
                '}';
    }
}
