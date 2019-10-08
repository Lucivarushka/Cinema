package main.java.com.moviehouse.service;

import main.java.com.moviehouse.model.Film;
import main.java.com.moviehouse.model.Seat;
import main.java.com.moviehouse.model.Ticket;
import main.java.com.moviehouse.repository.TicketRepository;
import main.java.com.moviehouse.repository.io.JavaIOTicketRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class TicketService {
    private static TicketRepository ticketRepository = new JavaIOTicketRepositoryImpl();

    public void createNewTicket(Ticket ticketNew) throws IOException {
        ticketRepository.create(ticketNew);
    }

    public void deleteTicket(Long index) throws IOException {
        ticketRepository.delete(index);
    }

    public List<Ticket> getSearchTicket(String search) throws IOException {
        return ticketRepository.getSearch(search);
    }

    public List<Ticket> getAllTicket() throws IOException {
        return ticketRepository.getAll();
    }

    public Long getPriceForTicket(Film film, Seat seat) throws IOException {
        return ticketRepository.getPriceTicket(film, seat);
    }
}
