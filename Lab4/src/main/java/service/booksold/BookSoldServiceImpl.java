package service.booksold;

import model.Book;
import model.BookSold;
import repository.Book.BookRepository;
import repository.BookSold.BookSoldRepository;

import java.util.List;
import java.util.Optional;

public class BookSoldServiceImpl implements BookSoldService{
    private final BookSoldRepository bookSoldRepository;

    public BookSoldServiceImpl(BookSoldRepository bookSoldRepository)
    {
        this.bookSoldRepository = bookSoldRepository;
    }
    @Override
    public void addBookSold(Book book) {
        bookSoldRepository.addBookSold(book);
    }

    @Override
    public void updateNrOfBooksSold(BookSold bookSold) {
          bookSoldRepository.updateNrOfBooksSold(bookSold);
    }

    @Override
    public Optional<BookSold> findBookById(Book book) {
       return  bookSoldRepository.findBookById(book);

    }

    @Override
    public List<BookSold> findAll() {
        return bookSoldRepository.findAll();
    }
}
