package main.java.com.moviehouse.manager;

import main.java.com.moviehouse.model.Seat;
import main.java.com.moviehouse.repository.SeatRepository;
import main.java.com.moviehouse.repository.io.JavaIOSeatRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class SeatManager {
    private static SeatRepository seatRepository = new JavaIOSeatRepositoryImpl();

    public void getSeat(int index) throws IOException {
        seatRepository.getAllFreeSeat(index);
    }

    public Seat getTakenSeat(int frSeat, int idTicket) throws IOException {
        return seatRepository.getTakenSeat(frSeat, idTicket);
    }

    public List<Seat> getSearchSeat(String search) throws IOException {
        return seatRepository.getSearch(search);
    }

    public List<Seat> getAllSeat() throws IOException {
        return seatRepository.getAll();
    }

    public void getEmptySeats() throws IOException {
        seatRepository.emptySeats();
    }
}
