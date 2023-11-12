package repository.book.Book;

import repository.book.Book.BookRepository;

public abstract class  BookRepositoryDecorator implements BookRepository {
    protected BookRepository decoratedRepository;

    public BookRepositoryDecorator(BookRepository decoratedRepository)
    {

        this.decoratedRepository=decoratedRepository;
    }

}
