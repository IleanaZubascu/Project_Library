package service.book;

import model.Book;
import repository.Book.BookRepository;
import service.book.BookService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Book book)
    {
        bookRepository.deleteBook(book);
    }

    @Override
    public int getAgeOfBook(Long id) {
        Book book = this.findById(id);

        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.YEARS.between(book.getPublishedDate(), now);
    }

    @Override
    public void updateStockBook(Long id, int stock) {
        bookRepository.updateStockBook(id, stock);

    }
    public void updateBook(Book book)
    {
        bookRepository.updateBook(book);
    }



}