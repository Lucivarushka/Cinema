package main.java.com.moviehouse.view;

import main.java.com.moviehouse.manager.FilmManager;
import main.java.com.moviehouse.manager.SeatManager;
import main.java.com.moviehouse.manager.TicketManager;
import main.java.com.moviehouse.model.Film;
import main.java.com.moviehouse.model.Seat;
import main.java.com.moviehouse.model.Ticket;
import main.java.com.moviehouse.repository.TicketRepository;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static main.java.com.moviehouse.model.Seat.TypeSeat.ECONOMY;

public class TicketView {
    private TicketManager ticketManager = new TicketManager();

    private FilmManager filmManager = new FilmManager();
    private SeatManager seatManager = new SeatManager();
    private Random random = new Random();

    public static final String ACTIONS_TICKET = "Введите действие:\n" +
            "1.Возврат билета\n" +
            "2.Поиск билета\n" +
            "3.Список всех билетов\n";

    public void createTicket(long filmID) throws IOException {
        int idTicket = 80 + (int) (Math.random() * 8000);
        Film film = filmManager.getFilmID(filmID);
        System.out.println("Выберете свободные места");
        seatManager.getSeat(idTicket);
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int frSeat = scanner.nextInt();
        Seat seat = seatManager.getTakenSeat(frSeat, idTicket);
        long price = ticketManager.getPriceForTicket(film,seat);
        String filmForTicket = film.toStringForTicket();
        String seatForTicket = seat.toString();
        ticketManager.createNewTicket(new Ticket(idTicket, filmForTicket, seatForTicket,price));
        System.out.println("Билет успешно создан");
    }

    public void deleteTicket() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер билета для его возврата:");
        long indexForDelete = Long.parseLong(scanner.next());
        ticketManager.deleteTicket(indexForDelete);
    }

    public void searchTicket() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите любую информацию о билете:");
        String search = scanner.next();
        System.out.println(ticketManager.getSearchTicket(search));
    }

    public void getAllTickect() throws IOException {
        List<Ticket> ticket = ticketManager.getAllTicket();
        for (Ticket t : ticket) {
            System.out.println(t);
        }
    }
}
