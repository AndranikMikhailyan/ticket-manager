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
        routeDao.add(route);
    }

    @Override
    public void updateRoute(Route route) {
        routeDao.update(route);
    }

    @Override
    public void removeRoute(Long id) {
        routeDao.remove(id);
    }

    @Override
    public Route getRouteById(Long id) {
        return routeDao.getById(id);
    }

    @Override
    public List<Route> getListRoutes() {
        return routeDao.getList();
    }
}
