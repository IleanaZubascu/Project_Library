import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryMock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMockUnitTest {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepositoryMock();

        Book book = new BookBuilder()
                .setId(1L)
                .setAuthor("Liviu Rebreanu")
                .setTitle("Adam si Eva")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        Book book1 = new BookBuilder()
                .setId(2L)
                .setAuthor("Liviu Rebreanu")
                .setTitle("Ion")
                .setPublishedDate(LocalDate.of(2015, 6, 2))
                .build();
     bookRepository.save(book);
     bookRepository.save(book1);

        List<Book> books= bookRepository.findAll();
        System.out.println(books);

        Optional<Book> IDEbook=bookRepository.findById(1L);
        System.out.println(IDEbook);

        bookRepository.removeAll();
        List<Book> removeBooks= bookRepository.findAll();
        System.out.println(removeBooks);

    }
}