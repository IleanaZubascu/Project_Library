package model;

public class BookSold {

    private Long id;
    private Long idBook;
    private int nrOfBooksSold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public int getNrOfBooksSold() {
        return nrOfBooksSold;
    }

    public void setNrOfBooksSold(int nrOfBooksSold) {
        this.nrOfBooksSold = nrOfBooksSold;
    }
}
