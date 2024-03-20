package ua.edu.nung.pz.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import java.util.Objects;

public class User {
    public static final String USER_SESSION_NAME = "user";
    private String email;
    private String password;
    private String displayName;
    private String phone;
    private String city;
    private String street;

    public User() {}

    public User(String email, String password, String displayName, String phone, String city, String street) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.phone = phone;
        this.city = city;
        this.street = street;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "User{" +
                "Email='" + email + '\'' +
                ", Password='" + password + '\'' +
                ", Phone='" + phone + '\'' +
                ", City='" + city + '\'' +
                ", Street='" + street + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getCity(), user.getCity()) && Objects.equals(getStreet(), user.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getPhone(), getCity(), getStreet());
    }
}
