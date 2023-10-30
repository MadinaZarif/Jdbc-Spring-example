package ru.itgirls.jdbcspringexample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirls.jdbcspringexample.Repository.BookRepository;
import ru.itgirls.jdbcspringexample.model.Book;

import java.util.List;


@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    public  BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @GetMapping("/book/all")
    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    @GetMapping("/book/{id}")
    public Book findBookById(@PathVariable("id") Long id) {
        return bookRepository.findBookById(id);
    }
}
