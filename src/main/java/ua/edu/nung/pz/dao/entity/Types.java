package ua.edu.nung.pz.dao.entity;

import java.util.Objects;

public class Types {
    private long id;
    private String name;
    private String deleted_at;

    public Types() {
    }

    public Types(long id, String name, String deleted_at) {
        this.id = id;
        this.name = name;
        this.deleted_at = deleted_at;
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

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Types)) return false;
        Types that = (Types) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getDeleted_at(), that.getDeleted_at());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDeleted_at());
    }

    @Override
    public String toString() {
        return "GoodsTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }
}
