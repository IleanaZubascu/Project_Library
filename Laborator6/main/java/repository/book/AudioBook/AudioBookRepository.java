package repository.book.AudioBook;

import model.AudioBook;
import model.Book;

import java.util.List;
import java.util.Optional;

public interface AudioBookRepository {
    List<AudioBook> findAll();

    Optional<AudioBook> findById(Long id);

    boolean save(AudioBook audioBook);

    void removeAll();
}
