package edu.java.ticketmanager.dao;

import edu.java.ticketmanager.model.Passenger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDaoImpl implements IPassengerDao {

    private static final String ADD_PASSENGER = "insert into passengers (last_name, first_name, patronymic, birth_day) value (?, ?, ?, ?)";

    private static final String UPDATE_PASSENGER = "update passengers set last_name = ?, first_name = ?, patronymic = ?, birth_day = ? where id = ?";

    private static final String REMOVE_PASSENGER = "delete from passengers where id = ?";

    private static final String GET_PASSENGER = "select * from passengers where id = ?";

    private static final String GET_PASSENGERS_LIST = "select * from passengers";

    @Override
    public void addPassenger(Passenger passenger) {
        try (Connection connection = ConnectionBuilder.getConnection();
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
    public void updatePassenger(Passenger passenger) {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSENGER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, passenger.getLastName());
            statement.setString(2, passenger.getFirstName());
            statement.setString(3, passenger.getPatronymic());
            statement.setDate(4, java.sql.Date.valueOf(passenger.getBirthDay()));
            statement.setInt(5, passenger.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePassenger(int id) {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_PASSENGER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Passenger getPassengerById(int id) {
        Passenger passenger = null;
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PASSENGER)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passenger = fillPassenger(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public List<Passenger> getListPassengers() {
        List<Passenger> passengersList = new ArrayList<>();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PASSENGERS_LIST)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                passengersList.add(fillPassenger(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengersList;
    }

    private Passenger fillPassenger(ResultSet resultSet) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setId(resultSet.getInt("id"));
        passenger.setLastName(resultSet.getString("last_name"));
        passenger.setFirstName(resultSet.getString("first_name"));
        passenger.setPatronymic(resultSet.getString("patronymic"));
        passenger.setBirthDay(resultSet.getDate("birth_day").toLocalDate());
        return passenger;
    }
}
