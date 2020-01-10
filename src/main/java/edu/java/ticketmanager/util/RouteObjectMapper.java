package edu.java.ticketmanager.util;

import edu.java.ticketmanager.model.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteObjectMapper {

    public static Route toRoute(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getLong("r.id"));
        route.setDepartureCity(resultSet.getString("r.departure_city"));
        route.setArrivalCity(resultSet.getString("r.arrival_city"));
        return null;
    }
}
