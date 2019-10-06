package main.java.com.moviehouse.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {

    void create(T t) throws IOException;

    void createCSVFile(List<T> list);

    void delete(ID id) throws IOException;

    List<T> getSearch(String search) throws IOException;

    List<T> getCSVParser() throws IOException;

    List<T> getAll() throws IOException;

}
