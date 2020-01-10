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
        ticketDao.add(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketDao.update(ticket);
    }

    @Override
    public void removeTicket(Long id) {
        ticketDao.remove(id);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketDao.getById(id);
    }

    @Override
    public List<Ticket> getListTickets() {
        return ticketDao.getList();
    }
}
