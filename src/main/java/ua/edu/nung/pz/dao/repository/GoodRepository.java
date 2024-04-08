package ua.edu.nung.pz.dao.repository;

import ua.edu.nung.pz.dao.entity.Good;
import ua.edu.nung.pz.dao.entity.Price;
import ua.edu.nung.pz.dao.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GoodRepository {
    public ArrayList<Good> getAll() {
        DataSource dataSource = new DataSource();
        ArrayList<Good> goods = new ArrayList<>();
        String sql = "SELECT g.*, p.* " +
                    "FROM goods g " +
                    "LEFT JOIN prices p ON g.id = p.good_id " +
                    "ORDER BY p.created_at DESC";

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong(1),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("brand"),
                        new String[]{resultSet.getString("photo")},
                        resultSet.getInt("likes"),
                        new Price(
                                resultSet.getLong(7),
                                resultSet.getLong("good_id"),
                                resultSet.getDouble("from_supplier"),
                                resultSet.getDouble("for_client"),
                                resultSet.getInt("income"),
                                resultSet.getInt("outcome"),
                                resultSet.getString("created_at"),
                                resultSet.getString("deleted_at")
                        )
                );
                goods.add(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goods;
    }
}
