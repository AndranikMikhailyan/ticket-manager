package edu.java.ticketmanager.dao;

import edu.java.ticketmanager.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements ITicketDao {

    private static final String ADD_TICKET =
            "insert into tickets (route_id, departure_date, ticket_class, seat_number, ticket_status, price, passenger_id) " +
            " value (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_TICKET =
            "update tickets " +
            "set route_id = ?, departure_date = ?, ticket_class = ?, seat_number = ?,  ticket_status = ?, price = ?, passenger_id = ? " +
            "where id = ?";

    private static final String REMOVE_TICKET = "delete from tickets where id = ?";

    private static final String GET_TICKET =
            "select t.id, t.departure_date, t.ticket_class, t.seat_number, t.ticket_status, t.price, " +
            "r.id, r.departure_city, r.arrival_city, " +
            "p.id, p.last_name, p.first_name, p.patronymic, p.birth_day " +
            "from tickets t " +
            "inner join routes r on t.route_id = r.id " +
            "left join passengers p on t.passenger_id = p.id " +
            "where t.id = ?";

    private static final String GET_TICKETS_LIST =
            "select t.id, t.departure_date, t.ticket_class, t.seat_number, t.ticket_status, t.price, " +
            "r.id, r.departure_city, r.arrival_city, " +
            "p.id, p.last_name, p.first_name, p.patronymic, p.birth_day " +
            "from tickets t " +
            "inner join routes r on t.route_id = r.id " +
            "left join passengers p on t.passenger_id = p.id";

    @Override
    public void addTicket(Ticket ticket) {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TICKET, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, ticket.getRoute().getId());
            statement.setDate(2, java.sql.Date.valueOf(ticket.getDepartureDate()));
            statement.setString(3, ticket.getTicketClass().toString());
            statement.setInt(4, ticket.getSeat_number());
            statement.setString(5, ticket.getTicketStatus().toString());
            statement.setBigDecimal(6, ticket.getPrice());
            statement.setInt(7, ticket.getPassenger().getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, ticket.getRoute().getId());
            statement.setDate(2, java.sql.Date.valueOf(ticket.getDepartureDate()));
            statement.setString(3, ticket.getTicketClass().toString());
            statement.setInt(4, ticket.getSeat_number());
            statement.setString(5, ticket.getTicketStatus().toString());
            statement.setBigDecimal(6, ticket.getPrice());
            statement.setInt(7, ticket.getPassenger().getId());
            statement.setInt(8, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTicket(int id) {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_TICKET, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getTicketById(int id) {
        Ticket ticket = null;
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_TICKET)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ticket = fillTicket(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List<Ticket> getListTickets() {
        List<Ticket> ticketsList = new ArrayList<>();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_TICKETS_LIST)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                ticketsList.add(fillTicket(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketsList;
    }

    private Ticket fillTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt("t.id"));
        ticket.setDepartureDate(resultSet.getDate("t.departure_date").toLocalDate());
        ticket.setTicketClass(TicketClass.valueOf(resultSet.getString("t.ticket_class")));
        ticket.setSeat_number(resultSet.getInt("t.seat_number"));
        ticket.setTicketStatus(TicketStatus.valueOf(resultSet.getString("t.ticket_status")));
        ticket.setPrice(resultSet.getBigDecimal("t.price"));
        Route route = new Route();
        route.setId(resultSet.getInt("r.id"));
        route.setDepartureCity(resultSet.getString("r.departure_city"));
        route.setArrivalCity(resultSet.getString("r.arrival_city"));
        ticket.setRoute(route);
        int passengerId = resultSet.getInt("p.id");
        if (passengerId != 0) {
            Passenger passenger = new Passenger();
            passenger.setId(passengerId);
            passenger.setLastName(resultSet.getString("p.last_name"));
            passenger.setFirstName(resultSet.getString("p.first_name"));
            passenger.setPatronymic(resultSet.getString("p.patronymic"));
            passenger.setBirthDay(resultSet.getDate("p.birth_day").toLocalDate());
            ticket.setPassenger(passenger);
        }
        return ticket;
    }
}
