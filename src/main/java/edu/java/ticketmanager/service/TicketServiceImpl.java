package edu.java.ticketmanager.service;

import edu.java.ticketmanager.dao.ITicketDao;
import edu.java.ticketmanager.model.Ticket;

import java.util.List;

public class TicketServiceImpl implements ITicketService {

    private ITicketDao ticketDao;

    public TicketServiceImpl(ITicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketDao.updateTicket(ticket);
    }

    @Override
    public void removeTicket(int id) {
        ticketDao.removeTicket(id);
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketDao.getTicketById(id);
    }

    @Override
    public List<Ticket> getListTickets() {
        return ticketDao.getListTickets();
    }
}
