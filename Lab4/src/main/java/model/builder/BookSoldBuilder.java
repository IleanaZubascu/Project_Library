package model.builder;

import model.Book;
import model.BookSold;

import java.time.LocalDate;

public class BookSoldBuilder {

    private BookSold soldBook;

    public BookSoldBuilder(){
        soldBook = new BookSold();
    }

    public BookSoldBuilder setId(Long id){
        soldBook.setId(id);
        return this;
    }

    public BookSoldBuilder setIdBook(Long idBook){
        soldBook.setIdBook(idBook);
        return this;
    }

    public BookSoldBuilder setNrOfBookSold(int nrOfBooksSold){
        soldBook.setNrOfBooksSold(nrOfBooksSold);
        return this;
    }


    public BookSold build(){
        return soldBook;
    }
}
