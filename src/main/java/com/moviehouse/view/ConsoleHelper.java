package main.java.com.moviehouse.view;

import java.io.IOException;
import java.util.Scanner;

import static main.java.com.moviehouse.view.FilmView.ACTIONS_FILM;
import static main.java.com.moviehouse.view.SeatView.ACTIONS_SEAT;
import static main.java.com.moviehouse.view.TicketView.ACTIONS_TICKET;

public class ConsoleHelper {
    public static final String ACTIONS_MOVIE = "Введите действие:\n" +
            "1.Посмотреть киноафишу и купить билет\n" +
            "2.Управление билетами\n" +
            "3.Управление фильмами\n" +
            "4.Управление местами\n" +
            "5.Выход\n";

    public static void run() throws IOException {
        System.out.println(ACTIONS_MOVIE);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        switch (line) {
            case "1":
                TicketView ticketView = new TicketView();
                FilmView filmView = new FilmView();
                System.out.println("Введите номер ID фильма");
                filmView.getMovies();
                long selectedMovie = Long.parseLong(scanner.next());
                ticketView.createTicket(selectedMovie);
                break;
            case "2":
                TicketView ticketViewForAction = new TicketView();
                System.out.println(ACTIONS_TICKET);
                String actionTicket = scanner.next();
                switch (actionTicket) {
                    case "1":
                        ticketViewForAction.deleteTicket();
                        break;
                    case "2":
                        ticketViewForAction.searchTicket();
                        break;
                    case "3":
                        ticketViewForAction.getAllTickect();
                        break;
                    case "4":
                        break;
                }
                break;
            case "3":
                FilmView filmViewfilm = new FilmView();
                System.out.println(ACTIONS_FILM);
                String actionCategory = scanner.next();
                switch (actionCategory) {
                    case "1":
                        filmViewfilm.createFilm();
                        break;
                    case "2":
                        filmViewfilm.deleteTicket();
                        break;
                    case "3":
                        filmViewfilm.searchFilm();
                        break;
                    case "4":
                        filmViewfilm.getAllFilm();
                        break;
                }
                break;
            case "4":
                SeatView seatView = new SeatView();
                System.out.println(ACTIONS_SEAT);
                String actionSeat = scanner.next();
                switch (actionSeat) {
                    case "1":
                        System.out.println("Введите любую известную информацию о месте");
                        String search = scanner.nextLine();
                        seatView.getSearchSeat(search);
                        break;
                    case "2":
                        seatView.emptySeat();
                        break;
                    case "3":
                        seatView.getAllSeat();
                        break;
                }
                break;
        }
    }
}
