package model;

import java.time.LocalDate;

// POJO - Plain Old Java Object
//Java Bean
public class Book{

    private Long id;

    private String author;

    private String title;

    private LocalDate publishedDate;

    private int price;

    private int stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString(){
        //return String.format("Book author: %s | title: %s | Published Date: %s. | Price: %s | Stock: %s", author, title, publishedDate, price, stock);
        return String.format("Book author: %s | title: %s | Price: %s", author, title, price);
    }

    public void toStringView(){
        System.out.println(String.format("Book author: %s | title: %s | Published Date: %s. | Price: %s | Stock: %s", author, title, publishedDate, price, stock));
    }

}
