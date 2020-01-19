package edu.java.ticketmanager.dao.hibernate;

import edu.java.ticketmanager.dao.HibernateUtil;
import edu.java.ticketmanager.dao.IRouteDao;
import edu.java.ticketmanager.model.Route;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateRouteDaoImpl implements IRouteDao {
    @Override
    public void add(Route entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Route entity) {
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
        Route route = session.get(Route.class, aLong);
        session.remove(route);
        transaction.commit();
        session.close();
    }

    @Override
    public Route getById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Route route = session.get(Route.class, aLong);
        session.close();
        return route;
    }

    @Override
    public List<Route> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Route> routes = session.createQuery("FROM Route", Route.class).list();
        transaction.commit();
        session.close();
        return routes;
    }
}
