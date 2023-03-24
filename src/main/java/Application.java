import dao.CityDAO;
import dao.StuffDAO;
import dao.impl.CityDAOImpl;
import dao.impl.StuffDAOImpl;
import models.City;
import models.Stuff;

public class Application {

    public static void main(String[] args) {

        CityDAO cityDAO = new CityDAOImpl();
        StuffDAO stuffDAO = new StuffDAOImpl();


        City city = new City(6, "Springfield");
        Stuff liza = new Stuff(24, "Лиза", "Симпсон", "female", 23, city);
//        cityDAO.create(city);
//        System.out.println(cityDAO.getAllCities());

//        stuffDAO.updateStuffEntity(liza);

        System.out.println(cityDAO.getAllCities()); // если в toString оставить stufflist, то выдает ошибку failed to lazily - не разобрался


        //================================================================================

//        Stuff man = new Stuff("Лиза", "Симпсон", "female", 23, new City(2));

//        stuffDAO.create(man); // в консоли всё красное, но операция работает корректно

//        System.out.println(stuffDAO.readById(24)); // опять крсное, но выводит инфу, с лишними строками команд

//          List<Stuff> stuffs = stuffDAO.readAll();
//        for (Stuff stuff : stuffs) {
//            System.out.println(stuff);
//        } // в консоли обновил город там где был налл у id 7 и список выводится корректно.

//        stuffDAO.updateStuffEntity(new Stuff(17,"Мардж", "Симпсон", "female",35,3)); //объект изменен. НО зачем так сложно?))) если у меня подключена базаданных через встроенный плагин и я могу в идее как в табличке Эксель всё менять?)))))

        //  stuffDAO.deleteStuffEntity(new Stuff(2)); // добавил конструктор толькок с id, всё удалилось)
        /*try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            StuffDAO stuffDAO = new StuffDAOImpl(connection);

            City city = new City(2);
            Stuff man = new Stuff("Барт", "Симпсон", "male", 23, city);
            //добавление в БД
//            stuffDAO.create(man); - добавлено

            List<Stuff> list = new ArrayList<>(stuffDAO.readAll());

            for (Stuff stuff : list) {
                System.out.println(stuff);
            } // список печается


//            stuffDAO.deleteEntityById(22); // удаление работает

            stuffDAO.updateEntityById(2, 1); // изменение города работает.

            System.out.println("stuffDAO.readById(2) = " + stuffDAO.readById(2)); // получение по id работает


        } catch (SQLException e) {
            e.printStackTrace();
        }*/


        /*        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

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
        }*/
    }
}