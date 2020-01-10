package edu.java.ticketmanager.util;

import edu.java.ticketmanager.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketObjectMapper {

    public static Ticket toTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("t.id"));
        ticket.setDepartureDate(resultSet.getDate("t.departure_date").toLocalDate());
        ticket.setTicketClass(TicketClass.valueOf(resultSet.getString("t.ticket_class")));
        ticket.setSeat_number(resultSet.getInt("t.seat_number"));
        ticket.setTicketStatus(TicketStatus.valueOf(resultSet.getString("t.ticket_status")));
        ticket.setPrice(resultSet.getBigDecimal("t.price"));
        Route route = RouteObjectMapper.toRoute(resultSet);
        ticket.setRoute(route);
        long passengerId = resultSet.getLong("p.id");
        if (passengerId != 0) {
            Passenger passenger = PassengerObjectMapper.toPassenger(resultSet);
            ticket.setPassenger(passenger);
        }
        return ticket;
    }
}
