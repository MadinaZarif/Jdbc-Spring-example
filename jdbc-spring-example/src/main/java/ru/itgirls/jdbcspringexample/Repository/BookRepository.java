package ru.itgirls.jdbcspringexample.Repository;

import ru.itgirls.jdbcspringexample.model.Book;

import java.util.List;

public interface BookRepository {
    List <Book> findAllBooks();

    Book findBookById(Long id);
}
