package ua.edu.nung.pz.dao.entity;

import java.util.Objects;

public class Order {
    private long id;
    private long user_id;
    private long price_id;
    private boolean is_paid;
    private String created_at;
    private String delete_at;

    public Order() {
    }

    public Order(long id, long user_id, long price_id, boolean is_paid, String created_at, String delete_at) {
        this.id = id;
        this.user_id = user_id;
        this.price_id = price_id;
        this.is_paid = is_paid;
        this.created_at = created_at;
        this.delete_at = delete_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getPrice_id() {
        return price_id;
    }

    public void setPrice_id(long price_id) {
        this.price_id = price_id;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(String delete_at) {
        this.delete_at = delete_at;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", price_id=" + price_id +
                ", is_paid=" + is_paid +
                ", created_at='" + created_at + '\'' +
                ", delete_at='" + delete_at + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() && getUser_id() == order.getUser_id() && getPrice_id() == order.getPrice_id() && isIs_paid() == order.isIs_paid() && Objects.equals(getCreated_at(), order.getCreated_at()) && Objects.equals(getDelete_at(), order.getDelete_at());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser_id(), getPrice_id(), isIs_paid(), getCreated_at(), getDelete_at());
    }
}
