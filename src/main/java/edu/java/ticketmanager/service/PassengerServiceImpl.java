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
        passengerDao.addPassenger(passenger);
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        passengerDao.updatePassenger(passenger);
    }

    @Override
    public void removePassenger(int id) {
        passengerDao.removePassenger(id);
    }

    @Override
    public Passenger getPassengerById(int id) {
        return passengerDao.getPassengerById(id);
    }

    @Override
    public List<Passenger> getListPassengers() {
        return passengerDao.getListPassengers();
    }
}
