package edu.java.ticketmanager.dao;

import edu.java.ticketmanager.model.Passenger;

import java.util.List;

public interface IPassengerDao {

    void addPassenger(Passenger passenger);

    void updatePassenger(Passenger passenger);

    void removePassenger(int id);

    Passenger getPassengerById(int id);

    List<Passenger> getListPassengers();
}
