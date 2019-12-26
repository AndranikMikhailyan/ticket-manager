package edu.java.ticketmanager.service;

import edu.java.ticketmanager.model.Passenger;

import java.util.List;

public interface IPassengerService {

    void addPassenger(Passenger passenger);

    void updatePassenger(Passenger passenger);

    void removePassenger(int id);

    Passenger getPassengerById(int id);

    List<Passenger> getListPassengers();
}
