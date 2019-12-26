package edu.java.ticketmanager.dao;

import edu.java.ticketmanager.model.Ticket;

import java.util.List;

public interface ITicketDao {

    void addTicket(Ticket route);

    void updateTicket(Ticket route);

    void removeTicket(int id);

    Ticket getTicketById(int id);

    List<Ticket> getListTickets();
}
