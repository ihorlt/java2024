package ua.edu.nung.pz.dao.entity;

import java.util.Arrays;
import java.util.Objects;

public class Good {
    private long id;
    private String name;
    private String description;
    private String brand;
    private String[] photo;
    private int likes;

    private Price price;

    public Good() {
    }

    public Good(long id, String name, String description, String brand, String[] photo, int likes, Price price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.photo = photo;
        this.likes = likes;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        if (description == null || description.isEmpty()) {
            return "";
        }
        int firstDotIndex = description.indexOf(".");
        int maxLength = 200;

        if(firstDotIndex != -1 && firstDotIndex <= maxLength) {
            return description.substring(0, firstDotIndex + 1);
        }
        return description.substring(0, Math.min(description.length(), maxLength));
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
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
        return getLikes() == good.getLikes() && Objects.equals(getName(), good.getName()) && Objects.equals(getBrand(), good.getBrand());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getDescription(), getBrand(), getLikes());
        result = 31 * result + Arrays.hashCode(getPhoto());
        return result;
    }
}
