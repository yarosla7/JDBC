import java.sql.*;

public class Application {

    public static void main(String[] args) {
        final String user = "postgres";
        final String pass = "4theMoon";
        final String url = "jdbc:postgresql://localhost:5432/employee";



        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Statement statement = connection.createStatement();

            int id = 1; // id работника, которого нужно получить

            // Запрос для получения данных из таблицы stuff и city
            String sql = "SELECT s.first_name, s.last_name, s.age, s.gender, c.city_name " +
                    "FROM stuff s " +
                    "JOIN city c ON s.city_id = c.city_id " +
                    "WHERE s.id = " + id;

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("first_name"));
                System.out.println("Surname: " + resultSet.getString("last_name"));
                System.out.println("Gender: " + resultSet.getString(4));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("City: " + resultSet.getString("city_name"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}