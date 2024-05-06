package ua.edu.nung.pz.dao.repository;

import ua.edu.nung.pz.dao.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
