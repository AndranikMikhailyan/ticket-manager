package edu.java.ticketmanager.dao.jdbc;

import edu.java.ticketmanager.dao.ConnectionBuilder;
import edu.java.ticketmanager.dao.IRouteDao;
import edu.java.ticketmanager.model.Route;
import edu.java.ticketmanager.util.RouteObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRouteDaoImpl implements IRouteDao {

    private static final String ADD_ROUTE = "insert into routes (departure_city, arrival_city) value (?, ?)";

    private static final String UPDATE_ROUTE = "update routes set departure_city = ?, arrival_city = ? where id = ?";

    private static final String REMOVE_ROUTE = "delete from routes where id = ?";

    private static final String GET_ROUTE = "select r.id, r.departure_city, r.arrival_city from routes r where id = ?";

    private static final String GET_ROUTES_LIST = "select r.id, r.departure_city, r.arrival_city from routes r";

    @Override
    public void add(Route route) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ROUTE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, route.getDepartureCity());
            statement.setString(2, route.getArrivalCity());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Route route) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ROUTE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, route.getDepartureCity());
            statement.setString(2, route.getArrivalCity());
            statement.setLong(3, route.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_ROUTE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Route getById(Long id) {
        Route route = null;
        try (Connection connection = edu.java.ticketmanager.dao.ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ROUTE)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                route = RouteObjectMapper.toRoute(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
    }

    @Override
    public List<Route> getList() {
        List<Route> routesList = new ArrayList<>();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ROUTES_LIST)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                routesList.add(RouteObjectMapper.toRoute(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routesList;
    }
}
