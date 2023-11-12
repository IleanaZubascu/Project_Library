import model.AudioBook;
import model.Book;
import model.EBook;
import model.builder.AudioBookBuilder;
import model.builder.BookBuilder;
import model.builder.EBookBuilder;
import repository.book.AudioBook.AudioBookRepository;
import repository.book.AudioBook.AudioBookRepositoryMock;
import repository.book.Book.BookRepository;
import repository.book.Book.BookRepositoryMock;
import repository.book.EBook.EBookRepository;
import repository.book.EBook.EBookRepositoryMock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MockTests {
    public static void main(String[] args) {
//BOOK TEST
//        BookRepository bookRepository = new BookRepositoryMock();
//
//        Book book = new BookBuilder()
//                .setId(1L)
//                .setAuthor("Liviu Rebreanu")
//                .setTitle("Adam si Eva")
//                .setPublishedDate(LocalDate.of(2010, 6, 2))
//                .build();
//        Book book1 = new BookBuilder()
//                .setId(2L)
//                .setAuthor("Liviu Rebreanu")
//                .setTitle("Ion")
//                .setPublishedDate(LocalDate.of(2015, 6, 2))
//                .build();
//     bookRepository.save(book);
//     bookRepository.save(book1);
//
//        List<Book> books= bookRepository.findAll();
//        System.out.println(books);
//
//        Optional<Book> IDEbook=bookRepository.findById(1L);
//        System.out.println(IDEbook);
//
//        bookRepository.removeAll();
//        List<Book> removeBooks= bookRepository.findAll();
//        System.out.println(removeBooks);

 //EBOOK TEST
        EBookRepository eBookRepository = new EBookRepositoryMock();

        EBook eBook = new EBookBuilder()
                .setId(1L)
                .setAuthor("Kazuo Isyguro")
                .setTitle("Sa nu ma parasesti")
                .setFormat("EPUB")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        eBookRepository.save(eBook);

        List<EBook> eBooks= eBookRepository.findAll();
        System.out.println(eBooks);

        Optional<EBook> IDEeBook=eBookRepository.findById(1L);
        System.out.println(IDEeBook);

        eBookRepository.removeAll();
        List<EBook> removeEBooks= eBookRepository.findAll();
        System.out.println(removeEBooks);
    //AUDIO BOOK TEST
        AudioBookRepository audioBookRepository = new AudioBookRepositoryMock();

        AudioBook audioBook = new AudioBookBuilder()
                .setId(1L)
                .setAuthor("Paul Kalanithi")
                .setTitle("Cu ultima suflare")
                .setRunTime(10000)
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        audioBookRepository.save(audioBook);

        List<AudioBook> audioBooks= audioBookRepository.findAll();
        System.out.println(audioBooks);

        Optional<AudioBook> IDAudioBook=audioBookRepository.findById(1L);
        System.out.println(IDAudioBook);

        audioBookRepository.removeAll();
        List<AudioBook> removeAudioBooks= audioBookRepository.findAll();
        System.out.println(removeAudioBooks);

    }
}