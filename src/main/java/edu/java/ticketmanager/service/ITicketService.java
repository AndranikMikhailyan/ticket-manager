package edu.java.ticketmanager.service;

import edu.java.ticketmanager.model.Ticket;

import java.util.List;

public interface ITicketService {

    void addTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void removeTicket(Long id);

    Ticket getTicketById(Long id);

    List<Ticket> getListTickets();
}
