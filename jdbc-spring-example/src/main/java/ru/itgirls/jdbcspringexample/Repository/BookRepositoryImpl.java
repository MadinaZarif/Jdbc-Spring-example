package ru.itgirls.jdbcspringexample.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itgirls.jdbcspringexample.model.Book;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Repository

public class BookRepositoryImpl implements BookRepository {
    @Autowired
    private DataSource dataSource;

    public BookRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Book> findAllBooks() {

        List<Book> result = new ArrayList<>();
        String SQL_findAllBooks = "select * from books;";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_findAllBooks)) {
            while (resultSet.next()) {
                Book book = converRowToBook(resultSet);
                result.add(book);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public Book findBookById(Long id) {
        String SQL_findBookById = "select * from books where id = ?;";
        try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from books where id =" + id + ";")) {
            while (resultSet.next()) {
                Book book = convertRowToBookId(resultSet);
                return book;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return null;
    }

    private Book convertRowToBookId(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Book(id, name);
    }

    private Book converRowToBook(ResultSet resultSet) throws SQLException {
    Long id = resultSet.getLong("id");
    String name = resultSet.getString("name");
    return new Book(id, name);
    }
}
