package service.book;

import model.Book;
import model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface BookService {

     List<Book> findAll();
     Book findById(Long id);

     boolean save(Book book);

     int getAgeOfBook(Long id);

     void updateStockBook(Long id, int stock);
    void updateBook(Book book);
     public void deleteBook(Book book);

}