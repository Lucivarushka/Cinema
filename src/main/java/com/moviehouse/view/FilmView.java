package main.java.com.moviehouse.view;

import main.java.com.moviehouse.model.TypeGenre;
import main.java.com.moviehouse.service.FilmService;
import main.java.com.moviehouse.model.Film;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FilmView {
    private FilmService filmService = new FilmService();
    private Scanner scanner = new Scanner(System.in);

    public static final String ACTIONS_FILM = "Введите действие:\n" +
            "1.Добавить новый фильм\n" +
            "2.Удалить фильм\n" +
            "3.Поиск фильма\n" +
            "4.Список всех фильмов\n";

    public void getMovies() throws IOException {
        List<Film> films = filmService.getAllFilm();
        for (Film f : films) {
            System.out.println("ID: " + f.getIdFilm() + " Название: " + f.getNameFilm());
        }
    }

    public void createFilm() throws IOException {
        System.out.println("Введите ID фильма:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите название нового фильма:");
        String name = scanner.nextLine();
        TypeGenre type = null;
        System.out.println("Введите жанр фильма (большими буквами): ADVENTURE, ROMANCE, COMEDIE, CRIME, WAR, DRAMA");
        String genre = scanner.nextLine();
        switch (genre) {
            case "ADVENTURE":
                type = TypeGenre.ADVENTURE;
            case "ROMANCE":
                type = TypeGenre.ROMANCE;
            case "COMEDIE":
                type = TypeGenre.COMEDIE;
            case "CRIME":
                type = TypeGenre.CRIME;
            case "WAR":
                type = TypeGenre.WAR;
            case "DRAMA":
                type = TypeGenre.DRAMA;
        }
        System.out.println("Введите страну производителя");
        String country = scanner.nextLine();
        System.out.println("Ведите дату премьеры (в формате пр. 2019-10-30)");
        String date = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(date);
        System.out.println("Введите цену за эконом билет");
        int economy = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите цену за VIP билет");
        int vip = Integer.parseInt(scanner.nextLine());
        filmService.createNewFilm(new Film(id, name, type, country, localDate, economy, vip));
        System.out.println("Фильм успешно добавлен");
    }

    public void deleteTicket() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID фильма для его удаления:");
        long indexForDelete = Long.parseLong(scanner.next());
        filmService.deleteFilm(indexForDelete);
    }

    public void searchFilm() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите любую информацию о фильме:");
        String search = scanner.next();
        filmService.getSearchFilm(search);
    }

    public void getAllFilm() throws IOException {
        List<Film> films = filmService.getAllFilm();
        for (Film f : films) {
            System.out.println(f);
        }
    }
}
