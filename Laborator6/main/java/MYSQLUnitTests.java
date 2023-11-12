import com.mysql.cj.jdbc.ConnectionWrapper;
import database.JDBConnectionWrapper;
import model.AudioBook;
import model.Book;
import model.EBook;
import model.builder.AudioBookBuilder;
import model.builder.BookBuilder;
import model.builder.EBookBuilder;
import repository.book.AudioBook.AudioBookRepository;
import repository.book.AudioBook.AudioBookRepositoryMock;
import repository.book.AudioBook.AudioBookRepositoryMySQL;
import repository.book.Book.BookRepository;
import repository.book.Book.BookRepositoryCacheDecorator;
import repository.book.Book.BookRepositoryMySQL;
import repository.book.Cache;
import repository.book.EBook.EBookRepository;
import repository.book.EBook.EBookRepositoryMySQL;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MYSQLUnitTests {
    public static void main(String[] args){

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");

//
//
//        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connectionWrapper.getConnection()),
//                new Cache<Book>());
//
//        Book book = new BookBuilder()
//                .setAuthor("IDK")
//                .setTitle("Fram Ursul Polar")
//                .setPublishedDate(LocalDate.of(2010, 6, 2))
//                .build();
//
//        bookRepository.save(book);
//
//       Optional<Book> Idbook=bookRepository.findById(2L);
//
//
//
//        System.out.println(bookRepository.findAll());
//
//        System.out.println(Idbook);
       // bookRepository.removeAll();

//EBOOK TEST
//      EBookRepository eBookRepository=new EBookRepositoryMySQL( connectionWrapper.getConnection());
//
//        EBook eBook = new EBookBuilder()
//                .setAuthor("Kazuo Isyguro")
//                .setTitle("Sa nu ma parasesti")
//                .setFormat("EPUB")
//                .setPublishedDate(LocalDate.of(2010, 6, 2))
//                .build();
//        eBookRepository.save(eBook);
//        List<EBook> eBooks= eBookRepository.findAll();
//
//        Optional<EBook> IDEeBook=eBookRepository.findById(1L);

//        eBookRepository.removeAll();
//        List<EBook> removeEBooks= eBookRepository.findAll();
//
//        System.out.println(removeEBooks);
// AUDIO book TEST
        AudioBookRepository audioBookRepository = new AudioBookRepositoryMySQL(connectionWrapper.getConnection());

        AudioBook audioBook = new AudioBookBuilder()
                .setId(1L)
                .setAuthor("Paul Kalanithi")
                .setTitle("Cu ultima suflare")
                .setRunTime(10000)
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        audioBookRepository.save(audioBook);
    }
}