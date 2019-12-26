package edu.java.ticketmanager.dao;

import edu.java.ticketmanager.model.Route;

import java.util.List;

public interface IRouteDao {

    void addRoute(Route route);

    void updateRoute(Route route);

    void removeRoute(int id);

    Route getRouteById(int id);

    List<Route> getListRoutes();
}
