import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;

import java.time.LocalDate;
import java.util.Optional;

public class BookRepositoryMYSQLUnitTest {
    public static void main(String[] args){

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");



        BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());

        Book book = new BookBuilder()
                .setAuthor("IDK")
                .setTitle("Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();

        bookRepository.save(book);

       Optional<Book> Idbook=bookRepository.findById(2L);



        System.out.println(bookRepository.findAll());

        System.out.println(Idbook);
       // bookRepository.removeAll();


    }
}