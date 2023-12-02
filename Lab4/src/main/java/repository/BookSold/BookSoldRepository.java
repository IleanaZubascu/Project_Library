package repository.BookSold;

import model.Book;
import model.BookSold;

import java.util.List;
import java.util.Optional;

public interface BookSoldRepository {

    void addBookSold(Book book);
    void updateNrOfBooksSold(BookSold bookSold);
    Optional<BookSold> findBookById(Book book);

    List<BookSold> findAll();

}
