package repository.book.EBook;

import model.Book;
import model.EBook;

import java.util.List;
import java.util.Optional;

public interface EBookRepository {
    List<EBook> findAll();

    Optional<EBook> findById(Long id);

    boolean save(EBook eBook);

    void removeAll();
}
