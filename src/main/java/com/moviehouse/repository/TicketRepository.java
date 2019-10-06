package main.java.com.moviehouse.repository;

import main.java.com.moviehouse.model.Film;
import main.java.com.moviehouse.model.Seat;
import main.java.com.moviehouse.model.Ticket;

import java.io.IOException;

public interface TicketRepository extends GenericRepository<Ticket, Long> {
    Long getPriceTicket(Film film, Seat seat) throws IOException;
}
