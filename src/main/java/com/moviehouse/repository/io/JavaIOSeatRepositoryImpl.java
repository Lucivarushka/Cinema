package main.java.com.moviehouse.repository.io;

import main.java.com.moviehouse.model.Seat;
import main.java.com.moviehouse.model.Seat.TypeSeat;
import main.java.com.moviehouse.repository.SeatRepository;
import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JavaIOSeatRepositoryImpl implements SeatRepository {
    private static String FILE_SEATS = "C:\\cinema1\\src\\main\\resources\\seats.csv";

    @Override
    public void create(Seat newSeat) throws IOException{
        List<Seat> nSeat = getCSVParserS();
        nSeat.add(newSeat);
        Collections.sort(nSeat);
        createCSVFileS(nSeat);
    }


    public void getAllFreeSeat(int idTicket) throws IOException {
        List<Seat> freeSeat = getCSVParser();
        for (Seat s : freeSeat) {
            if (s.getIdTicket() == 0) {
                System.out.print(s.getNumberSeat() + " " + s.getTypeSeat() + " ");
            }
        }
    }



    @Override
    public Seat getTakenSeat(int frSeat, int idTicket) throws IOException {
        Seat seat = new Seat(frSeat, idTicket, true);
        List<Seat> freeSeat = getCSVParser();
        for (Seat s : freeSeat) {
            if (s.getNumberSeat() == frSeat && s.getIdTicket() == 0) {
                delete(frSeat);
            }
        }
        create(seat);
        return seat;
    }

    @Override
    public void createCSVFile(List<Seat> seatForCSV) {
        ICsvBeanWriter beanWriter = null;

        try {
            beanWriter = new CsvBeanWriter(new FileWriter(FILE_SEATS),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = new String[]{"numberSeat", "rowSeat", "idTicket", "typeSeat", "taken"};

            final CellProcessor[] processors = getProcessors();
            beanWriter.writeHeader(header);

            for (Seat s : seatForCSV) {
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


    public static void createCSVFileS(List<Seat> seatForCSV) {
        ICsvBeanWriter beanWriter = null;

        try {
            beanWriter = new CsvBeanWriter(new FileWriter(FILE_SEATS),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = new String[]{"numberSeat", "rowSeat", "idTicket", "typeSeat", "taken"};

            final CellProcessor[] processors = getProcessors();
            beanWriter.writeHeader(header);

            for (Seat s : seatForCSV) {
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
    public void delete(Integer index) throws IOException {
        List<Seat> deleteSeat = getCSVParser();
        for (Seat ds : deleteSeat) {
            if (ds.getNumberSeat() == index) {
                deleteSeat.remove(ds);
            }
        }
        createCSVFile(deleteSeat);
    }

    @Override
    public List<Seat> getSearch(String search) throws IOException {
        List<Seat> foundSeats = new ArrayList<>();
        List<Seat> searchSeat = getCSVParser();

        for (Seat ss : searchSeat) {
            if (ss.toString().contains(search)) {
                foundSeats.add(ss);
            }
        }
        return foundSeats;
    }

    @Override
    public List<Seat> getCSVParser() throws IOException {
        List<Seat> seatsForParser = new CopyOnWriteArrayList<>();
        ICsvBeanReader csvBeanReader = new CsvBeanReader(new FileReader(FILE_SEATS),
                CsvPreference.STANDARD_PREFERENCE);

        try {
            final String[] header = csvBeanReader.getHeader(true);

            final CellProcessor[] processors = getProcessorsParsing();
            Seat seatParser;
            while ((seatParser = csvBeanReader.read(Seat.class, header, processors)) != null) {
                Seat addMe = new Seat(seatParser.getNumberSeat(), seatParser.getIdTicket(), seatParser.isTaken());
                seatsForParser.add(addMe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            csvBeanReader.close();
        }
        return seatsForParser;
    }


    public static List<Seat> getCSVParserS() throws IOException {
        List<Seat> seatsForParser = new CopyOnWriteArrayList<>();
        ICsvBeanReader csvBeanReader = new CsvBeanReader(new FileReader(FILE_SEATS),
                CsvPreference.STANDARD_PREFERENCE);

        try {
            final String[] header = csvBeanReader.getHeader(true);

            final CellProcessor[] processors = getProcessorsParsing();
            Seat seatParser;
            while ((seatParser = csvBeanReader.read(Seat.class, header, processors)) != null) {
                Seat addMe = new Seat(seatParser.getNumberSeat(), seatParser.getIdTicket(), seatParser.isTaken());
                seatsForParser.add(addMe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            csvBeanReader.close();
        }
        return seatsForParser;
    }

    @Override
    public List<Seat> getAll() throws IOException {
        return getCSVParser();
    }

    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new UniqueHashCode(),
                new NotNull(),
                new NotNull(),
                new ParseEnum(TypeSeat.class, true),
                new Optional(new FmtBool("Y", "N")),
        };
    }

    private static CellProcessor[] getProcessorsParsing() {
        return new CellProcessor[]{
                new UniqueHashCode(new ParseInt()),
                new NotNull(new ParseInt()),
                new NotNull(new ParseInt()),
                new ParseEnum(TypeSeat.class, true),
                new ParseBool(),
        };
    }

    public void emptySeats() {
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(1, 0, false));
        seats.add(new Seat(2, 0, false));
        seats.add(new Seat(3, 0, false));
        seats.add(new Seat(4, 0, false));
        seats.add(new Seat(5, 0, false));
        seats.add(new Seat(6, 0, false));
        seats.add(new Seat(7, 0, false));
        seats.add(new Seat(8, 0, false));
        seats.add(new Seat(9, 0, false));
        seats.add(new Seat(10, 0, false));
        seats.add(new Seat(11, 0, false));
        seats.add(new Seat(12, 0, false));
        seats.add(new Seat(13, 0, false));
        seats.add(new Seat(14, 0, false));
        seats.add(new Seat(15, 0, false));
        seats.add(new Seat(16, 0, false));
        createCSVFile(seats);
    }
}
