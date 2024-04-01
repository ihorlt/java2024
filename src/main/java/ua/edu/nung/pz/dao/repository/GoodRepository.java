package ua.edu.nung.pz.dao.repository;

import ua.edu.nung.pz.dao.entity.Good;
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
        String sql = "Select * FROM goods";

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("brand"),
                        new String[]{resultSet.getString("photo")},
                        resultSet.getInt("likes")
                );

                goods.add(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goods;
    }
}
