package edu.java.ticketmanager.dao.jdbc;

import edu.java.ticketmanager.dao.ConnectionBuilder;
import edu.java.ticketmanager.dao.ITicketDao;
import edu.java.ticketmanager.model.*;
import edu.java.ticketmanager.util.TicketObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTicketDaoImpl implements ITicketDao {

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
    public void add(Ticket ticket) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TICKET, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, ticket.getRoute().getId());
            statement.setDate(2, java.sql.Date.valueOf(ticket.getDepartureDate()));
            statement.setString(3, ticket.getTicketClass().toString());
            statement.setInt(4, ticket.getSeatNumber());
            statement.setString(5, ticket.getTicketStatus().toString());
            statement.setBigDecimal(6, ticket.getPrice());
            statement.setLong(7, ticket.getPassenger().getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, ticket.getRoute().getId());
            statement.setDate(2, java.sql.Date.valueOf(ticket.getDepartureDate()));
            statement.setString(3, ticket.getTicketClass().toString());
            statement.setInt(4, ticket.getSeatNumber());
            statement.setString(5, ticket.getTicketStatus().toString());
            statement.setBigDecimal(6, ticket.getPrice());
            statement.setLong(7, ticket.getPassenger().getId());
            statement.setLong(8, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_TICKET, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getById(Long id) {
        Ticket ticket = null;
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_TICKET)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ticket = TicketObjectMapper.toTicket(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List<Ticket> getList() {
        List<Ticket> ticketsList = new ArrayList<>();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_TICKETS_LIST)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                ticketsList.add(TicketObjectMapper.toTicket(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketsList;
    }
}
