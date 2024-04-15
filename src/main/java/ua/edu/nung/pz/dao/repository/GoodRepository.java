package ua.edu.nung.pz.dao.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ua.edu.nung.pz.dao.entity.Good;
import ua.edu.nung.pz.dao.entity.Price;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GoodRepository {

    public ArrayList<Good> getByBrand(String brand) {
        if (brand == null || brand.isEmpty()) {
            return new ArrayList<Good>();
        }
        String capitalized = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        return getByCondition(" WHERE p.deleted_at IS NULL AND g.brand = '" + capitalized + "'", "");
    }

    public ArrayList<Good> getByLikes(boolean isLike) {
        String order = isLike ? " ORDER BY p.likes DESC " : " ORDER BY p.likes ASC ";
        return getByCondition(" WHERE p.deleted_at IS NULL ", order);
    }

    private ArrayList<Good> getByCondition(String where, String order) {
        DataSource dataSource = new DataSource();
        ArrayList<Good> goods = new ArrayList<>();
        if (order == null || order.isEmpty()) {
            order = " ORDER BY p.created_at DESC";
        }
        String sql = "SELECT g.*, p.* " +
                    "FROM goods g " +
                    "LEFT JOIN prices p ON g.id = p.good_id " +
                     where + order;

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
                        resultSet.getLong(1),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("brand"),
                        photos,
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
