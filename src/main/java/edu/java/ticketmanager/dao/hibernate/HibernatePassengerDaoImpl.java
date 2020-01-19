package edu.java.ticketmanager.dao.hibernate;

import edu.java.ticketmanager.dao.HibernateUtil;
import edu.java.ticketmanager.dao.IPassengerDao;
import edu.java.ticketmanager.model.Passenger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class HibernatePassengerDaoImpl implements IPassengerDao {
    @Override
    public void add(Passenger entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Passenger entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Passenger passenger = session.get(Passenger.class, aLong);
        session.remove(passenger);
        transaction.commit();
        session.close();
    }

    @Override
    public Passenger getById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Passenger passenger = session.get(Passenger.class, aLong);
        session.close();
        return passenger;
    }

    @Override
    public List<Passenger> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Passenger> passengers = session.createQuery("FROM Passenger", Passenger.class).list();
        transaction.commit();
        session.close();
        return passengers;
    }
}
