package ua.edu.nung.pz.model;

import java.util.Arrays;
import java.util.Objects;

public class Good {
    private long id;
    private String name;
    private String type;
    private String description;
    private Double price;
    private Double discount;
    private String brand;
    private String[] photo;
    private int likes;

    public Good() {
    }

    public Good(long id, String name, String type, String description, Double price, Double discount, String brand, String[] photo, int likes) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.brand = brand;
        this.photo = photo;
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] getPhoto() {
        return photo;
    }

    public void setPhoto(String[] photo) {
        this.photo = photo;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", brand='" + brand + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", likes=" + likes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Good)) return false;
        Good good = (Good) o;
        return getLikes() == good.getLikes() && Objects.equals(getName(), good.getName()) && Objects.equals(getType(), good.getType()) && Objects.equals(getPrice(), good.getPrice()) && Objects.equals(getDiscount(), good.getDiscount()) && Objects.equals(getBrand(), good.getBrand());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getType(), getDescription(), getPrice(), getDiscount(), getBrand(), getLikes());
        result = 31 * result + Arrays.hashCode(getPhoto());
        return result;
    }
}
