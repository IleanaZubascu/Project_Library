package view;

import javafx.beans.property.*;
import model.Book;

import java.time.LocalDate;

public class BookView {
    private final LongProperty id;
    private final StringProperty author;
    private final StringProperty title;
    private final ObjectProperty<LocalDate> publishedDate;
    private final IntegerProperty price;
    private final IntegerProperty stock;

    public BookView(Book book) {
        this.id = new SimpleLongProperty(book.getId());
        this.author = new SimpleStringProperty(book.getAuthor());
        this.title = new SimpleStringProperty(book.getTitle());
        this.publishedDate = new SimpleObjectProperty<>(book.getPublishedDate());
        this.price = new SimpleIntegerProperty(book.getPrice());
        this.stock = new SimpleIntegerProperty(book.getStock());
    }

    // Getters for JavaFX properties
    public LongProperty idProperty() {
        return id;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public ObjectProperty<LocalDate> publishedDateProperty() {
        return publishedDate;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public IntegerProperty stockProperty() {
        return stock;
    }
}
