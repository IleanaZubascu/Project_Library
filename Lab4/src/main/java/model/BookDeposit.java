package model;


public class BookDeposit {

    private Long idBook;
    private int stock;
    private int pricePerBook;

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPricePerBook() {
        return pricePerBook;
    }

    public void setPricePerBook(int pricePerBook) {
        this.pricePerBook = pricePerBook;
    }
}
