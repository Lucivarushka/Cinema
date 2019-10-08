package main.java.com.moviehouse.repository.io;

import main.java.com.moviehouse.model.Film;
import main.java.com.moviehouse.model.Seat;
import main.java.com.moviehouse.model.Ticket;
import main.java.com.moviehouse.model.TypeSeat;
import main.java.com.moviehouse.repository.TicketRepository;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
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

import static main.java.com.moviehouse.model.TypeSeat.ECONOMY;

public class JavaIOTicketRepositoryImpl implements TicketRepository {
    private static String FILE_TICKETS = "\\src\\main\\resources\\tickets.csv";

    @Override
    public void create(Ticket newTicket) throws IOException {
        List<Ticket> tickets = getCSVParser();
        tickets.add(newTicket);
        createCSVFile(tickets);
    }

    @Override
    public void createCSVFile(List<Ticket> ticketForCSV) {
        ICsvBeanWriter beanWriter = null;

        try {
            beanWriter = new CsvBeanWriter(new FileWriter(FILE_TICKETS),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = new String[]{"numberTicket", "date", "film", "seat", "price"};

            final CellProcessor[] processors = getProcessors();
            beanWriter.writeHeader(header);

            for (Ticket s : ticketForCSV) {
                beanWriter.write(s, header, processors);
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
        List<Ticket> deleteTicket = getCSVParser();
        for (Ticket dt : deleteTicket) {
            if (dt.getNumberTicket() == aLong) {
                deleteTicket.remove(dt);
            }
        }
        createCSVFile(deleteTicket);
    }

    @Override
    public List<Ticket> getSearch(String search) throws IOException {
        List<Ticket> foundTicket = new ArrayList<>();
        List<Ticket> searchTicket = getCSVParser();

        for (Ticket st : searchTicket) {
            if (st.toString().contains(search)) {
                foundTicket.add(st);
            }
        }
        return foundTicket;
    }

    @Override
    public List<Ticket> getCSVParser() throws IOException {
        List<Ticket> ticketForParser = new CopyOnWriteArrayList<>();
        ICsvBeanReader csvBeanReader = new CsvBeanReader(new FileReader(FILE_TICKETS),
                CsvPreference.STANDARD_PREFERENCE);

        try {
            final String[] header = csvBeanReader.getHeader(true);

            final CellProcessor[] processors = getProcessorsParsing();
            Ticket ticketParser;
            while ((ticketParser = csvBeanReader.read(Ticket.class, header, processors)) != null) {
                Ticket addMe = new Ticket(ticketParser.getNumberTicket(), ticketParser.getFilm(), ticketParser.getSeat(),
                        ticketParser.getPrice());
                ticketForParser.add(addMe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            csvBeanReader.close();
        }
        return ticketForParser;
    }

    @Override
    public List<Ticket> getAll() throws IOException {
        return getCSVParser();
    }

    private CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new UniqueHashCode(),
                new FmtLocalDate("yyyy-MM-dd"),
                new Optional(),
                new Optional(),
                new ParseLong()
        };
    }

    private CellProcessor[] getProcessorsParsing() {
        return new CellProcessor[]{
                new UniqueHashCode(new ParseInt()),
                new ParseLocalDate(),
                new NotNull(),
                new NotNull(),
                new NotNull(new ParseLong())
        };
    }

    @Override
    public Long getPriceTicket(Film film, Seat seat) throws IOException {
        TypeSeat ts = seat.getTypeSeat();
        long price;
        if (ts.equals(ECONOMY)) {
            price = film.getPriceForEconomy();
        } else {
            price = film.getPriceForVip();
        }
        return price;
    }
}
