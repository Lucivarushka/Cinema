package main.java.com.moviehouse.view;

import main.java.com.moviehouse.service.FilmService;
import main.java.com.moviehouse.service.SeatService;
import main.java.com.moviehouse.service.TicketService;
import main.java.com.moviehouse.model.Film;
import main.java.com.moviehouse.model.Seat;
import main.java.com.moviehouse.model.Ticket;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TicketView {
    private TicketService ticketService = new TicketService();
    private FilmService filmService = new FilmService();
    private SeatService seatService = new SeatService();

    public static final String ACTIONS_TICKET = "Введите действие:\n" +
            "1.Возврат билета\n" +
            "2.Поиск билета\n" +
            "3.Список всех билетов\n";

    public void createTicket(long filmID) throws IOException {
        int idTicket = 80 + (int) (Math.random() * 8000);
        Film film = filmService.getFilmID(filmID);
        System.out.println("Выберете свободные места");
        seatService.getSeat(idTicket);
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int frSeat = scanner.nextInt();
        Seat seat = seatService.getTakenSeat(frSeat, idTicket);
        long price = ticketService.getPriceForTicket(film, seat);
        String filmForTicket = film.toStringForTicket();
        String seatForTicket = seat.toString();
        ticketService.createNewTicket(new Ticket(idTicket, filmForTicket, seatForTicket, price));
        System.out.println("Билет успешно создан");
    }

    public void deleteTicket() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер билета для его возврата:");
        long indexForDelete = Long.parseLong(scanner.next());
        ticketService.deleteTicket(indexForDelete);
    }

    public void searchTicket() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите любую информацию о билете:");
        String search = scanner.next();
        System.out.println(ticketService.getSearchTicket(search));
    }

    public void getAllTickect() throws IOException {
        List<Ticket> ticket = ticketService.getAllTicket();
        for (Ticket t : ticket) {
            System.out.println(t);
        }
    }
}
