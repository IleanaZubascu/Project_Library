package model.builder;

import model.AudioBook;
import model.Book;

import java.time.LocalDate;
import java.util.Date;

public class AudioBookBuilder {
    private AudioBook audioBook;

    public AudioBookBuilder() {
        audioBook = new AudioBook();
    }

    public AudioBookBuilder setId(Long id) {
        audioBook.setId(id);
        return this;
    }

    public AudioBookBuilder setAuthor(String author) {
        audioBook.setAuthor(author);
        return this;
    }

    public AudioBookBuilder setTitle(String title) {
        audioBook.setTitle(title);
        return this;
    }

    public AudioBookBuilder setRunTime(int runTime)
    {
        audioBook.setRunTime(runTime);
        return this;
    }
    public AudioBookBuilder setPublishedDate(LocalDate publishedDate) {
        audioBook.setPublishedDate(publishedDate);
        return this;
    }
    public AudioBook build(){
        return audioBook;
    }
}
