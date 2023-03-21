package dao.impl;

import dao.StuffDAO;
import models.City;
import models.Stuff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StuffDAOImpl implements StuffDAO {

    private final Connection connection;

    public StuffDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Stuff stuff) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO stuff (first_name, last_name, gender, age, city_id)" +
                        "VALUES ((?), (?), (?), (?), (?))"
        )) {

            statement.setString(1, stuff.getFirst_name());
            statement.setString(2, stuff.getLast_name());
            statement.setString(3, stuff.getGender());
            statement.setInt(4, stuff.getAge());
            statement.setInt(5, stuff.getCity().getCity_id());

            statement.executeQuery();
            System.out.println("Объект добавлен в БД.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stuff readById(int id) {
        Stuff stuff = new Stuff();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM stuff INNER JOIN city ON stuff.city_id = city.city_id AND id = (?)"
        )) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                stuff.setId(resultSet.getInt("id"));
                stuff.setFirst_name(resultSet.getString(2));
                stuff.setLast_name(resultSet.getString(3));
                stuff.setGender(resultSet.getString(4));
                stuff.setAge(resultSet.getInt(5));
                stuff.setCity(new City(resultSet.getInt("city_id"), resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stuff;
    }

    @Override
    public List<Stuff> readAll() {
        List<Stuff> stuffList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM stuff INNER JOIN city ON stuff.city_id = city.city_id"
        )) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString(2);
                String last_name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                int age = resultSet.getInt(5);
                City city = new City(resultSet.getInt("city_id"), resultSet.getString("city_name"));

                stuffList.add(new Stuff(id, first_name, last_name, gender, age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stuffList;
    }

    @Override
    public void updateEntityById(int id, int cityId) {

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE stuff SET city_id = (?) WHERE id = (?)"
        )) {

            statement.setInt(1, cityId);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntityById(int id) {

        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM stuff WHERE id = (?)"
        )) {
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}