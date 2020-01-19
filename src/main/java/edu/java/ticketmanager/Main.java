package edu.java.ticketmanager;

import edu.java.ticketmanager.dao.HibernateUtil;
import edu.java.ticketmanager.dao.hibernate.HibernateRouteDaoImpl;
import edu.java.ticketmanager.dao.hibernate.HibernateTicketDaoImpl;
import org.hibernate.SessionFactory;

/**
 * Временный класс для тестов
 */
public class Main {

    public static void main(String[] args) {
//        HibernateTicketDaoImpl hibernateTicketDao = new HibernateTicketDaoImpl();
//
//        System.out.println(hibernateTicketDao.getById(1L));
//        hibernateTicketDao.getList().forEach(ticket -> System.out.println(ticket));
        HibernateRouteDaoImpl hibernateRouteDao = new HibernateRouteDaoImpl();

        System.out.println(hibernateRouteDao.getById(1L));
    }
}
