package dao.impl;

import dao.StuffDAO;
import factory.HibernateSessionFactoryUtil;
import models.Stuff;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Stuff.class, id);
    }

    @Override
    public List<Stuff> readAll() {

        return (List<Stuff>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM Stuff").list();
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