package main.java.com.moviehouse.repository;

import main.java.com.moviehouse.model.Film;

import java.io.IOException;

public interface FilmRepository extends GenericRepository<Film, Long> {

    Film getIDFilm(Long aLong) throws IOException;

}
