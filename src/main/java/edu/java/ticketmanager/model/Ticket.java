package edu.java.ticketmanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Ticket {

    private Long id;
    private Route route;
    private LocalDate departureDate;
    private TicketClass ticketClass;
    private int seat_number;
    private TicketStatus ticketStatus;
    private BigDecimal price;
    private Passenger passenger;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public TicketClass getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", route=" + route +
                ", departureDate=" + departureDate +
                ", ticketClass=" + ticketClass +
                ", seat_number=" + seat_number +
                ", ticketStatus=" + ticketStatus +
                ", price=" + price +
                ", passenger=" + passenger +
                '}';
    }
}
