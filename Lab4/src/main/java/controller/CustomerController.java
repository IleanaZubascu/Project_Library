package controller;

import com.mysql.cj.jdbc.ConnectionWrapper;
import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.Book;
import model.BookSold;
import repository.Book.BookRepository;
import repository.Book.BookRepositoryCacheDecorator;
import repository.Book.BookRepositoryMySQL;
import repository.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.booksold.BookSoldService;
import view.CustomerView;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CustomerController {

    private final CustomerView customerView;

    private final BookService bookService;

    private final BookSoldService bookSoldService;

    public CustomerController(CustomerView customerView,BookService bookService,BookSoldService bookSoldService) {
        this.customerView = customerView;
        this.bookService=bookService;
        this.bookSoldService=bookSoldService;

        this.customerView.addviewButtonListener(new viewBooksButton());
        this.customerView.addBuyBookButtonListener(new buyBookButton());
        this.customerView.addBuyButtonListener(new buyButton());

    }

    private class viewBooksButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
           customerView.setTableVisibleTrue();
           customerView.setBookChoiceBoxVisileFalse();

        }
    }


    private class buyBookButton implements  EventHandler<ActionEvent>{
        @Override
        public void handle(javafx.event.ActionEvent event) {

           customerView.setBookChoiceBoxVisibleTrue();
           customerView.setTableVisibleFalse();


        }

    }
    private class buyButton implements  EventHandler<ActionEvent>{
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Book book=customerView.getBookFromBuyBook();
            book.toStringView();

            if(book.getStock()!=0)
            {
                bookService.updateStockBook(book.getId(), (book.getStock()-1));
                customerView.setActionTargetText("Buy Successfull!");
                Optional<BookSold> bookSold=bookSoldService.findBookById(book);

                if(bookSold.isPresent())
                {
                    bookSoldService.updateNrOfBooksSold(bookSold.get());
                }
                else {
                    bookSoldService.addBookSold(book);
                }






            }
            else{

                customerView.setActionTargetText("Book out of stock!");
            }

        }

    }

}