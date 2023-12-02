package repository.Book;

import model.Book;
import model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    boolean save(Book book);

    void removeAll();

    void updateStockBook(Long id, int stock);

    void deleteBook(Book book);

    void updateBook(Book book);

}