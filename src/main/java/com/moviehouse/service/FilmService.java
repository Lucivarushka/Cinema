package main.java.com.moviehouse.service;

import main.java.com.moviehouse.model.Film;
import main.java.com.moviehouse.repository.FilmRepository;
import main.java.com.moviehouse.repository.io.JavaIOFilmRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class FilmService {
    private static FilmRepository filmRepository = new JavaIOFilmRepositoryImpl();

    public void createNewFilm(Film filmNew) throws IOException{
        filmRepository.create(filmNew);
    }

    public void deleteFilm(Long index) throws IOException {
        filmRepository.delete(index);
    }

    public Film getFilmID(Long aLong) throws IOException {
        return filmRepository.getIDFilm(aLong);
    }

    public List<Film> getSearchFilm(String search) throws IOException {
        return filmRepository.getSearch(search);
    }

    public List<Film> getAllFilm() throws IOException {
        return filmRepository.getAll();
    }
}

