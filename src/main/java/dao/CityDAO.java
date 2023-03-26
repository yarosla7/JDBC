package dao;

import models.City;

import java.util.List;

public interface CityDAO {
    City findCityById(int id);

    List<City> getAllCities();

    void create(City city);

    List getStuffListByCityId(int id);

    void update(City city);

    void delete(City city);
}
