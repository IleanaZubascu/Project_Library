package repository.Book;

import repository.Book.BookRepository;

public abstract class  BookRepositoryDecorator implements BookRepository {
    protected BookRepository decoratedRepository;

    public BookRepositoryDecorator(BookRepository decoratedRepository)
    {

        this.decoratedRepository=decoratedRepository;
    }

}
