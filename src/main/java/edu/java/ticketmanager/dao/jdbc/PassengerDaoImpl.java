package edu.java.ticketmanager.dao.jdbc;

import edu.java.ticketmanager.dao.ConnectionBuilder;
import edu.java.ticketmanager.dao.IPassengerDao;
import edu.java.ticketmanager.model.Passenger;
import edu.java.ticketmanager.util.PassengerObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDaoImpl implements IPassengerDao {

    private static final String ADD_PASSENGER = "insert into passengers (last_name, first_name, patronymic, birth_day) value (?, ?, ?, ?)";

    private static final String UPDATE_PASSENGER = "update passengers set last_name = ?, first_name = ?, patronymic = ?, birth_day = ? where id = ?";

    private static final String REMOVE_PASSENGER = "delete from passengers where id = ?";

    private static final String GET_PASSENGER = "select p.id, p.last_name, p.first_name, p.patronymic, p.birth_day from passengers p where id = ?";

    private static final String GET_PASSENGERS_LIST = "select p.id, p.last_name, p.first_name, p.patronymic, p.birth_day from passengers p";

    @Override
    public void add(Passenger passenger) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PASSENGER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, passenger.getLastName());
            statement.setString(2, passenger.getFirstName());
            statement.setString(3, passenger.getPatronymic());
            statement.setDate(4, java.sql.Date.valueOf(passenger.getBirthDay()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Passenger passenger) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSENGER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, passenger.getLastName());
            statement.setString(2, passenger.getFirstName());
            statement.setString(3, passenger.getPatronymic());
            statement.setDate(4, java.sql.Date.valueOf(passenger.getBirthDay()));
            statement.setLong(5, passenger.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_PASSENGER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Passenger getById(Long id) {
        Passenger passenger = null;
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PASSENGER)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passenger = PassengerObjectMapper.toPassenger(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public List<Passenger> getList() {
        List<Passenger> passengersList = new ArrayList<>();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PASSENGERS_LIST)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                passengersList.add(PassengerObjectMapper.toPassenger(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengersList;
    }
}
