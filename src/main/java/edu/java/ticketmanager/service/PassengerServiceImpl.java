package edu.java.ticketmanager.service;

import edu.java.ticketmanager.dao.IPassengerDao;
import edu.java.ticketmanager.model.Passenger;

import java.util.List;

public class PassengerServiceImpl implements IPassengerService {

    private IPassengerDao passengerDao;

    public PassengerServiceImpl(IPassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Override
    public void addPassenger(Passenger passenger) {
        passengerDao.add(passenger);
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        passengerDao.update(passenger);
    }

    @Override
    public void removePassenger(Long id) {
        passengerDao.remove(id);
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerDao.getById(id);
    }

    @Override
    public List<Passenger> getListPassengers() {
        return passengerDao.getList();
    }
}
