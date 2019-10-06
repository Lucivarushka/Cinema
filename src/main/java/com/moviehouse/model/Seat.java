package main.java.com.moviehouse.model;

public class Seat implements Comparable<Seat> {
    private int numberSeat;
    private int rowSeat;
    private int idTicket;
    private TypeSeat typeSeat;
    private boolean taken;

    public enum TypeSeat {
        ECONOMY, VIP
    }

    public Seat() {
    }

    public Seat(int numberSeat, int idTicket, boolean taken) {
        this.numberSeat = numberSeat;
        this.rowSeat = defineRowSeat();
        this.idTicket = idTicket;
        this.typeSeat = defineTypeSeat();
        this.taken = taken;
    }

    public int getNumberSeat() {
        return numberSeat;
    }

    public void setNumberSeat(int numberSeat) {
        this.numberSeat = numberSeat;
    }

    public int getRowSeat() {
        return rowSeat;
    }

    public void setRowSeat(int rowSeat) {
        this.rowSeat = rowSeat;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public TypeSeat getTypeSeat() {
        return typeSeat;
    }

    public void setTypeSeat(TypeSeat typeSeat) {
        this.typeSeat = typeSeat;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    private int defineRowSeat() {
        int defineSeat = 0;
        if (1 <= numberSeat && numberSeat <= 4) {
            defineSeat = 1;
        } else if (5 <= numberSeat && numberSeat <= 8) {
            defineSeat = 2;
        } else if (9 <= numberSeat && numberSeat <= 12) {
            defineSeat = 3;
        } else if (13 <= numberSeat & numberSeat <= 16) {
            defineSeat = 4;
        }
        return defineSeat;
    }

    private TypeSeat defineTypeSeat() {
        TypeSeat defineSeat;
        if (rowSeat <= 3) {
            defineSeat = TypeSeat.ECONOMY;
        } else {
            defineSeat = TypeSeat.VIP;
        }
        return defineSeat;
    }

    public String toString() {
        return getNumberSeat() + " " + getRowSeat() + " " + getIdTicket() + " " + getTypeSeat() + " " + isTaken();
    }

    @Override
    public int compareTo(Seat otherSeat) {
        return this.getNumberSeat() - otherSeat.getNumberSeat();
    }
}