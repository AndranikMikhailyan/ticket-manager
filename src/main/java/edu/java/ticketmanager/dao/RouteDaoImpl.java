package edu.java.ticketmanager.dao;

import edu.java.ticketmanager.model.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements IRouteDao {

    private static final String ADD_ROUTE = "insert into routes (departure_city, arrival_city) value (?, ?)";

    private static final String UPDATE_ROUTE = "update routes set departure_city = ?, arrival_city = ? where id = ?";

    private static final String REMOVE_ROUTE = "delete from routes where id = ?";

    private static final String GET_ROUTE = "select * from routes where id = ?";

    private static final String GET_ROUTES_LIST = "select * from routes";

    @Override
    public void addRoute(Route route) {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ROUTE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, route.getDepartureCity());
            statement.setString(2, route.getArrivalCity());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoute(Route route) {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ROUTE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, route.getDepartureCity());
            statement.setString(2, route.getArrivalCity());
            statement.setInt(3, route.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeRoute(int id) {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_ROUTE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Route getRouteById(int id) {
        Route route = null;
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ROUTE)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                route = fillRoute(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
    }

    @Override
    public List<Route> getListRoutes() {
        List<Route> routesList = new ArrayList<>();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ROUTES_LIST)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                routesList.add(fillRoute(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routesList;
    }

    private Route fillRoute(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt("id"));
        route.setDepartureCity(resultSet.getString("departure_city"));
        route.setArrivalCity(resultSet.getString("arrival_city"));
        return route;
    }

}
