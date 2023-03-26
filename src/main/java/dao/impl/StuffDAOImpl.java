package dao.impl;

import dao.StuffDAO;
import models.City;
import models.Stuff;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class StuffDAOImpl implements StuffDAO {
    @Override
    public void create(Stuff stuff) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(stuff);
            transaction.commit();
        }
    }

    @Override
    public Stuff readById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Stuff.class, id);
        }
    }

    @Override
    public List<Stuff> readByCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Stuff> query = session.createQuery("FROM Stuff s JOIN FETCH s.city c WHERE c.cityName = :city", Stuff.class);
            query.setParameter("city", city.getCityName());
            return query.list();
        }
    }

    @Override
    public List<Stuff> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT s FROM Stuff s LEFT JOIN FETCH s.city", Stuff.class).list();
        }
    }

    @Override
    public void updateStuffEntity(Stuff stuff) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(stuff);
            transaction.commit();
        }
    }

    @Override
    public void deleteStuffEntity(Stuff stuff) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(stuff);
            transaction.commit();
        }
    }
}