package main.java.com.moviehouse.repository.io;

import main.java.com.moviehouse.model.Film;
import main.java.com.moviehouse.model.Film.TypeGenre;
import main.java.com.moviehouse.repository.FilmRepository;
import org.joda.time.LocalDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseEnum;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.joda.FmtLocalDate;
import org.supercsv.cellprocessor.joda.ParseLocalDate;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JavaIOFilmRepositoryImpl implements FilmRepository {
    private static List<Film> films = new ArrayList<>();
    private static String FILE_FILMS = "C:\\cinema1\\src\\main\\resources\\films.csv";

    public static void main(String[] args) {
        Film h = new Film(5, "Join", Film.TypeGenre.CRIME, "USA",
                LocalDate.parse("2019-10-09"), 400, 700);
        createF(h);
    }

    static {
        films.add(new Film(1, "Spider man", TypeGenre.ADVENTURE, "France",
                LocalDate.parse("2019-10-02"), 900, 1700));
        films.add(new Film(2, "Suzanne", TypeGenre.DRAMA, "Denmark",
                LocalDate.parse("2019-09-12"), 500, 800));
        films.add(new Film(3, "Crash", TypeGenre.ROMANCE, "Italy",
                LocalDate.parse("2019-10-30"), 700, 1000));
        films.add(new Film(4, "SuperNova", Film.TypeGenre.COMEDIE, "UK",
                LocalDate.parse("2019-10-02"), 800, 1250));
    }

    public static void createF(Film newFilm) {
        films.add(newFilm);
        createCSV(films);
    }

    @Override
    public void create(Film newFilm) {
        films.add(newFilm);
        createCSVFile(films);
    }

    public static void createCSV(List<Film> filmsForCSV) {
        ICsvBeanWriter beanWriter = null;

        try {
            beanWriter = new CsvBeanWriter(new FileWriter(FILE_FILMS),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = new String[]{"idFilm", "NameFilm", "TypeGenre", "countryFilm", "releaseTime",
                    "priceForEconomy", "priceForVIP"};

            final CellProcessor[] processors = getProcessors();
            beanWriter.writeHeader(header);

            for (Film f : filmsForCSV) {
                beanWriter.write(f, header, processors);
            }

            beanWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                beanWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void createCSVFile(List<Film> filmsForCSV) {
        ICsvBeanWriter beanWriter = null;

        try {
            beanWriter = new CsvBeanWriter(new FileWriter(FILE_FILMS),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = new String[]{"idFilm", "NameFilm", "TypeGenre", "countryFilm", "releaseTime",
                    "priceForEconomy", "priceForVIP"};

            final CellProcessor[] processors = getProcessors();
            beanWriter.writeHeader(header);

            for (Film f : filmsForCSV) {
                beanWriter.write(f, header, processors);
            }

            beanWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                beanWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long aLong) throws IOException {
        List<Film> deleteFilms = getCSVParser();
        for (Film df : deleteFilms) {
            if (df.getIdFilm() == aLong) {
                deleteFilms.remove(df);
            }
        }
        createCSVFile(deleteFilms);
    }

    @Override
    public List<Film> getSearch(String search) throws IOException {
        List<Film> foundFilm = new ArrayList<>();
        List<Film> searchFilms = getCSVParser();

        for (Film sf : searchFilms) {
            if (sf.toString().contains(search)) {
                foundFilm.add(sf);
            }
        }
        return foundFilm;
    }

    @Override
    public List<Film> getCSVParser() throws IOException {
        List<Film> filmsForParser = new CopyOnWriteArrayList<>();
        ICsvBeanReader csvBeanReader = new CsvBeanReader(new FileReader(FILE_FILMS),
                CsvPreference.STANDARD_PREFERENCE);

        try {
            final String[] header = csvBeanReader.getHeader(true);

            final CellProcessor[] processors = getProcessorsParsing();
            Film filmParser;
            while ((filmParser = csvBeanReader.read(Film.class, header, processors)) != null) {
                Film addMe = new Film(filmParser.getIdFilm(), filmParser.getNameFilm(), filmParser.getTypeGenre(),
                        filmParser.getCountryFilm(), filmParser.getReleaseTime(), filmParser.getPriceForEconomy(), filmParser.getPriceForVip());
                filmsForParser.add(addMe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            csvBeanReader.close();
        }
        return filmsForParser;
    }

    @Override
    public List<Film> getAll() throws IOException {
        return getCSVParser();
    }

    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new UniqueHashCode(),
                new NotNull(),
                new ParseEnum(TypeGenre.class, true),
                new NotNull(),
                new FmtLocalDate("yyyy-MM-dd"),
                new Optional(),
                new Optional()
        };
    }

    private static CellProcessor[] getProcessorsParsing() {
        return new CellProcessor[]{
                new UniqueHashCode(new ParseInt()),
                new NotNull(),
                new ParseEnum(TypeGenre.class, true),
                new NotNull(),
                new ParseLocalDate(),
                new Optional(new ParseInt()),
                new Optional(new ParseInt())
        };
    }

    @Override
    public Film getIDFilm(Long aLong) throws IOException {
        Film film = null;
        List<Film> films = getCSVParser();

        for (Film f : films) {
            if (f.getIdFilm() == aLong) {
                film = f;
            }
        }
        return film;
    }
}
