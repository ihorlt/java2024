package ua.edu.nung.pz.dao.entity;

import java.util.Objects;

/**
 * в консолі, де mysql - це назва сервісу у системі ver MySQL 8.0.31
 * mysql -u root
 * SHOW DATABASES;
 * DROP DATABASE your_schema_name;
 * CREATE DATABASE webapp CHARACTER SET utf8 COLLATE utf8_general_ci;
 * USE your_schema_name;
 * EXIT;
 */
public class User {
    public static final String USER_SESSION_NAME = "user";
    private long id;
    private String email;
    private String password;
    private String displayName;
    private String options;
    private String created_at;
    private String delete_at;

    public User() {}

    public User(long id, String email, String password, String displayName, String options, String created_at, String delete_at) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.options = options;
        this.created_at = created_at;
        this.delete_at = delete_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", displayName='" + displayName + '\'' +
                ", options='" + options + '\'' +
                ", created_at='" + created_at + '\'' +
                ", delete_at='" + delete_at + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getDisplayName(), user.getDisplayName()) && Objects.equals(getCreated_at(), user.getCreated_at()) && Objects.equals(getDelete_at(), user.getDelete_at());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getDisplayName());
    }
}
