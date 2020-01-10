package edu.java.ticketmanager.util;

import edu.java.ticketmanager.model.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerObjectMapper {

    public static Passenger toPassenger(ResultSet resultSet) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setId(resultSet.getLong("id"));
        passenger.setLastName(resultSet.getString("last_name"));
        passenger.setFirstName(resultSet.getString("first_name"));
        passenger.setPatronymic(resultSet.getString("patronymic"));
        passenger.setBirthDay(resultSet.getDate("birth_day").toLocalDate());
        return passenger;
    }
}
