package main.java.com.moviehouse.model;

import org.joda.time.LocalDate;

public class Ticket {
    private int numberTicket;
    private LocalDate date;
    private String film;
    private String seat;
    private long price;

    public Ticket() {
    }

    public Ticket(int numberTicket, String film, String seat, long price) {
        this.numberTicket = numberTicket;
        this.date = getDateNow();
        this.film = film;
        this.seat = seat;
        this.price = price;
    }

    public int getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(int numberTicket) {
        this.numberTicket = numberTicket;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    private LocalDate getDateNow() {
        return LocalDate.now();
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getNumberTicket() + " " + getDate() + " " + getFilm() + " " + getSeat() + " " + getPrice();
    }
}
