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
            session.close();
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
            Query query = session.createQuery("FROM Stuff s WHERE s.city = :city");
            query.setParameter("city", city);
            return (List<Stuff>) query.list();
        }
    }

    @Override
    public List<Stuff> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Stuff", Stuff.class).list();
        }
    }

    @Override
    public void updateStuffEntity(Stuff stuff) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(stuff);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void deleteStuffEntity(Stuff stuff) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(stuff);
            transaction.commit();
            session.close();
        }
    }
}