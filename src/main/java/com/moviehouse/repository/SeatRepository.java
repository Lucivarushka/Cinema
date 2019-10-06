package main.java.com.moviehouse.repository;

import main.java.com.moviehouse.model.Seat;

import java.io.IOException;

public interface SeatRepository extends GenericRepository<Seat, Integer> {

    void getAllFreeSeat(int idTicket) throws IOException;

    Seat getTakenSeat(int frSeat, int idTicket) throws IOException;

    void emptySeats() throws IOException;

}
