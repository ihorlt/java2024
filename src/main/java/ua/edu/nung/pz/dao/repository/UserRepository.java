package ua.edu.nung.pz.dao.repository;

import ua.edu.nung.pz.dao.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    public User getUserByEmail(String email) {
        DataSource dataSource = new DataSource();
        User user = new User();
        String sql = "Select * FROM users WHERE users.email='" + email + "'";

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setDisplayName(resultSet.getString("displayName"));
                user.setOptions(resultSet.getString("options"));
                user.setCreated_at(resultSet.getString("created_at"));
                user.setDelete_at(resultSet.getString("deleted_at"));
            } else {
                System.out.println("No data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
