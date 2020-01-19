package edu.java.ticketmanager.dao.hibernate;

import edu.java.ticketmanager.dao.HibernateUtil;
import edu.java.ticketmanager.dao.ITicketDao;
import edu.java.ticketmanager.model.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateTicketDaoImpl implements ITicketDao {
    @Override
    public void add(Ticket entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Ticket entity) {
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
        Ticket ticket = session.get(Ticket.class, aLong);
        session.remove(ticket);
        transaction.commit();
        session.close();
    }

    @Override
    public Ticket getById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Ticket ticket = session.get(Ticket.class, aLong);
        session.close();
        return ticket;
    }

    @Override
    public List<Ticket> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Ticket> tickets = session.createQuery("FROM Ticket", Ticket.class).list();
        transaction.commit();
        session.close();
        return tickets;
    }
}
