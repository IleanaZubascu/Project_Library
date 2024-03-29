import com.mysql.cj.jdbc.ConnectionWrapper;
import database.JDBConnectionWrapper;
import model.AudioBook;
import model.Book;
import model.EBook;
import model.builder.AudioBookBuilder;
import model.builder.BookBuilder;
import model.builder.EBookBuilder;
import repository.AudioBook.AudioBookRepository;
import repository.AudioBook.AudioBookRepositoryMock;
import repository.AudioBook.AudioBookRepositoryMySQL;
import repository.Book.BookRepository;
import repository.Book.BookRepositoryCacheDecorator;
import repository.Book.BookRepositoryMySQL;
import repository.Cache;
import repository.EBook.EBookRepository;
import repository.EBook.EBookRepositoryMySQL;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MYSQLUnitTests {
    public static void main(String[] args){

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("library");



        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connectionWrapper.getConnection()),
                new Cache<Book>());

        Book book = new BookBuilder()
                .setAuthor("IDK")
                .setTitle("Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .setPrice(20)
                .setStock(100)
                .build();

        Book book1 = new BookBuilder()
                .setAuthor("Fyodor Dostoievski")
                .setTitle("The Meek One")
                .setPublishedDate(LocalDate.of(1876, 12, 20))
                .setPrice(19)
                .setStock(50)
                .build();
        Book book2 = new BookBuilder()
                .setAuthor("Fyodor Dostoievski")
                .setTitle("Crime and punishment")
                .setPublishedDate(LocalDate.of(1866, 10, 6))
                .setPrice(19)
                .setStock(50)
                .build();
        Book book3 = new BookBuilder()
                .setAuthor("Edgar Allan Pow")
                .setTitle("Prabusirea Casei Usher")
                .setPublishedDate(LocalDate.of(1839, 11, 10))
                .setPrice(19)
                .setStock(50)
                .build();

        bookRepository.save(book);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);



//       Optional<Book> Idbook=bookRepository.findById(2L);
//
//        Book book3 = new BookBuilder()
//                .setAuthor("Edgar Allan Pow")
//                .setTitle("Pisica neagra")
//                .setPublishedDate(LocalDate.of(1839, 11, 10))
//                .setPrice(19)
//                .setStock(0)
//                .build();
//        bookRepository.save(book3);

//        System.out.println(bookRepository.findAll());
//
//        System.out.println(Idbook);
  //      Integer s=0;
//        bookRepository.removeAll();

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
//        AudioBookRepository audioBookRepository = new AudioBookRepositoryMySQL(connectionWrapper.getConnection());
//
//        AudioBook audioBook = new AudioBookBuilder()
//                .setId(1L)
//                .setAuthor("Paul Kalanithi")
//                .setTitle("Cu ultima suflare")
//                .setRunTime(10000)
//                .setPublishedDate(LocalDate.of(2010, 6, 2))
//                .build();
//        audioBookRepository.save(audioBook);
//        List<AudioBook> audioBooks= audioBookRepository.findAll();
//
//      Optional<AudioBook> IDEeBook=audioBookRepository.findById(1L);


   }
}