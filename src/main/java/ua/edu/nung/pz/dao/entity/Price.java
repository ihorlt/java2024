package ua.edu.nung.pz.dao.entity;

import java.util.Objects;

public class Price {
    private long id;

    private long good_id;
    private double from_supplier;
    private double for_client;

    // number of goods with the price
    private int income;

    // number of sold goods
    private int outcome;
    private String created_at;
    private String deleted_at;

    public Price() {
    }

    public Price(long id, long good_id, double from_supplier, double for_client, int income, int outcome, String created_at, String deleted_at) {
        this.id = id;
        this.good_id = good_id;
        this.from_supplier = from_supplier;
        this.for_client = for_client;
        this.income = income;
        this.outcome = outcome;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGood_id() {
        return good_id;
    }

    public void setGood_id(long good_id) {
        this.good_id = good_id;
    }

    public double getFrom_supplier() {
        return from_supplier;
    }

    public void setFrom_supplier(double from_supplier) {
        this.from_supplier = from_supplier;
    }

    public double getFor_client() {
        return for_client;
    }

    public void setFor_client(double for_client) {
        this.for_client = for_client;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", good_id=" + good_id +
                ", from_supplier=" + from_supplier +
                ", for_client=" + for_client +
                ", income=" + income +
                ", outcome=" + outcome +
                ", created_at='" + created_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price = (Price) o;
        return getId() == price.getId() && getGood_id() == price.getGood_id() && Double.compare(getFrom_supplier(), price.getFrom_supplier()) == 0 && Double.compare(getFor_client(), price.getFor_client()) == 0 && getIncome() == price.getIncome() && getOutcome() == price.getOutcome() && Objects.equals(getCreated_at(), price.getCreated_at()) && Objects.equals(getDeleted_at(), price.getDeleted_at());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGood_id(), getFrom_supplier(), getFor_client(), getIncome(), getOutcome(), getCreated_at(), getDeleted_at());
    }
}
