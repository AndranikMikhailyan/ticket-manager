package edu.java.ticketmanager.service;

import edu.java.ticketmanager.dao.IRouteDao;
import edu.java.ticketmanager.model.Route;

import java.util.List;

public class RouteServiceImpl implements IRouteService {

    private IRouteDao routeDao;

    public RouteServiceImpl(IRouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public void addRoute(Route route) {
        routeDao.addRoute(route);
    }

    @Override
    public void updateRoute(Route route) {
        routeDao.updateRoute(route);
    }

    @Override
    public void removeRoute(int id) {
        routeDao.removeRoute(id);
    }

    @Override
    public Route getRouteById(int id) {
        return routeDao.getRouteById(id);
    }

    @Override
    public List<Route> getListRoutes() {
        return routeDao.getListRoutes();
    }
}
