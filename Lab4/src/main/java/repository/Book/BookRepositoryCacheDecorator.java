package repository.Book;

import model.Book;
import repository.Book.BookRepository;
import repository.Book.BookRepositoryDecorator;
import repository.Cache;

import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator  extends BookRepositoryDecorator {
    private Cache<Book> cache;

    public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache){
        super(bookRepository);
        this.cache=cache;

    }

    @Override
    public List<Book> findAll() {
        if(cache.hasResult())
        {
            return cache.load();
        }

        List<Book> books=decoratedRepository.findAll();
        cache.save(books);
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {

        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(Book book) {
        cache.invalidateCache();
        return decoratedRepository.save(book);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();

    }

    @Override
    public void updateStockBook(Long id, int stock) {
        decoratedRepository.updateStockBook(id, stock);
    }

    @Override
    public void deleteBook(Book book) {
        decoratedRepository.deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
        decoratedRepository.updateBook(book);
    }


}
