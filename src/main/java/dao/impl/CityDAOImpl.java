package dao.impl;

import dao.CityDAO;
import models.City;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class CityDAOImpl implements CityDAO {
    @Override
    public City findCityById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> getAllCities() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM City", City.class).list();
        }
    }

    @Override
    public void create(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public List getStuffListByCityId(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            City city2 = session.get(City.class, id);
            Hibernate.initialize(city2.getStuffList());
            return city2.getStuffList();
        }
    }

    public void update(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    public void delete(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}