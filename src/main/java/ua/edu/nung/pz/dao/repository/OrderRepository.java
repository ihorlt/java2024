package ua.edu.nung.pz.dao.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ua.edu.nung.pz.dao.entity.Cart;
import ua.edu.nung.pz.dao.entity.Good;
import ua.edu.nung.pz.dao.entity.Order;
import ua.edu.nung.pz.dao.entity.Price;

import java.sql.*;
import java.util.ArrayList;

public class OrderRepository {
    private DataSource dataSource;

    public OrderRepository() {
        this.dataSource = new DataSource();
    }

    public void saveOrUpdate(Order order) {
        String sql = "INSERT INTO orders (user_id, price_id, is_paid, created_at, deleted_at) \n" +
                "values (?, ?, ?, ?, ?)";
        if (order.getId() != 0) {
            sql = "UPDATE orders SET user_id = ?, price_id = ?, is_paid = ?, created_at = ?, deleted_at = ? \n" +
            "WHERE id = ?";
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                )
        {
            pstmt.setLong(1, order.getUser_id());
            pstmt.setLong(2, order.getPrice_id());
            pstmt.setBoolean(3, order.isIs_paid());
            pstmt.setString(4, order.getCreated_at());
            pstmt.setString(5, order.getDelete_at());
            if (order.getId() != 0) {
                pstmt.setLong(6, order.getId());
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cart getOrdersByUserId(long userId) {
        DataSource dataSource = new DataSource();
        Cart cart = new Cart();
        ArrayList<Good> goods = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        if (userId == 0l) {
            return cart;
        }
        String sql = "SELECT g.*, p.*, o.* " +
                "FROM goods g " +
                "LEFT JOIN prices p ON g.id = p.good_id " +
                "LEFT JOIN orders o ON p.id = o.price_id " +
                "WHERE o.user_id = " + userId + " AND o.deleted_at IS NULL ";

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next()) {
                String photoJson = resultSet.getString("photo");
                JsonElement jsonElement = JsonParser.parseString(photoJson);
                String photos[] = new String[0];
                if (jsonElement.isJsonArray()) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    photos = new String[jsonArray.size()];
                    int i = 0;
                    for (JsonElement jsonElement1 : jsonArray) {
                        photos[i++] = jsonElement.getAsString();
                    }
                }
                Good good = new Good(
                        resultSet.getLong("g.id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("brand"),
                        photos,
                        resultSet.getInt("likes"),
                        new Price(
                                resultSet.getLong("p.id"),
                                resultSet.getLong("good_id"),
                                resultSet.getDouble("from_supplier"),
                                resultSet.getDouble("for_client"),
                                resultSet.getInt("income"),
                                resultSet.getInt("outcome"),
                                resultSet.getString("p.created_at"),
                                resultSet.getString("p.deleted_at")
                        )
                );
                Order order = new Order(
                        resultSet.getLong("o.id"),
                        resultSet.getLong("o.user_id"),
                        resultSet.getLong("o.price_id"),
                        resultSet.getBoolean("o.is_paid"),
                        resultSet.getString("o.created_at"),
                        resultSet.getString("o.deleted_at")
                );
                goods.add(good);
                orders.add(order);
            }
            cart.setGoods(goods.toArray(new Good[goods.size()]));
            cart.setOrders(orders.toArray(new Order[orders.size()]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }
}
