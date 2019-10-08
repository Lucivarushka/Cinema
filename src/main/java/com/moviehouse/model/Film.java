package main.java.com.moviehouse.model;

import org.joda.time.LocalDate;

public class Film {
    private int idFilm;
    private String nameFilm;
    private TypeGenre typeGenre;
    private String countryFilm;
    private LocalDate releaseTime;
    private int priceForEconomy;
    private int priceForVip;

    public Film() {
    }

    public Film(int idFilm, String nameFilm, TypeGenre typeGenre, String countryFilm, LocalDate releaseTime,
                int priceForEconomy, int priceForVip) {
        this.idFilm = idFilm;
        this.nameFilm = nameFilm;
        this.typeGenre = typeGenre;
        this.countryFilm = countryFilm;
        this.releaseTime = releaseTime;
        this.priceForEconomy = priceForEconomy;
        this.priceForVip = priceForVip;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public TypeGenre getTypeGenre() {
        return typeGenre;
    }

    public void setTypeGenre(TypeGenre typeGenre) {
        this.typeGenre = typeGenre;
    }

    public String getCountryFilm() {
        return countryFilm;
    }

    public void setCountryFilm(String countryFilm) {
        this.countryFilm = countryFilm;
    }

    public LocalDate getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDate releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getPriceForEconomy() {
        return priceForEconomy;
    }

    public void setPriceForEconomy(int priceForEconomy) {
        this.priceForEconomy = priceForEconomy;
    }

    public int getPriceForVip() {
        return priceForVip;
    }

    public void setPriceForVip(int priceForVip) {
        this.priceForVip = priceForVip;
    }

    public String toString() {
        return getIdFilm() + " " + getNameFilm() + " " + getTypeGenre() + " " + getCountryFilm() + " " + getReleaseTime()
                + " " + getPriceForEconomy() + " " + getPriceForVip();
    }

    public String toStringForTicket() {
        return getIdFilm() + " " + getNameFilm() + " " + getTypeGenre() + " " + getCountryFilm() + " " + getReleaseTime();
    }
}


