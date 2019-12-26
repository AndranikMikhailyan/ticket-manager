package edu.java.ticketmanager.service;

import edu.java.ticketmanager.model.Route;

import java.util.List;

public interface IRouteService {

    void addRoute(Route route);

    void updateRoute(Route route);

    void removeRoute(int id);

    Route getRouteById(int id);

    List<Route> getListRoutes();
}
