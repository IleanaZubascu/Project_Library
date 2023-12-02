package view;

import com.mysql.cj.xdevapi.FindStatementImpl;
import database.JDBConnectionWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Book;
import model.BookSold;
import model.builder.BookBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import repository.Book.BookRepository;
import repository.Book.BookRepositoryCacheDecorator;
import repository.Book.BookRepositoryMySQL;
import repository.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.booksold.BookSoldService;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class EmployeeView {

    private Button createBookButton;

    private Button deleteBookButton;

    private Button updateBookButton;

    private Button sellBookButton;

    private final Stage primaryStage;

    private final Scene scene;

    private Text actiontarget;

    //for create book

    private TextField authorField;
    private TextField titleField;
    private TextField publishedDateField;
    private TextField stockField;
    private TextField priceField;
    private Button addButton;


    //for delete book
    private ChoiceBox<Book> bookChoiceBox;
    private Button deleteButton;

    //for update book

   private Button updateButton;

   private final BookService bookService;

   private final BookSoldService bookSoldService;


    public EmployeeView(Stage primaryStage, BookService bookService,BookSoldService bookSoldService)
    {
        this.primaryStage=primaryStage;
        primaryStage.setTitle("Employee");

        this.bookService=bookService;
        this.bookSoldService=bookSoldService;

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);



        initializeSceneTitle(gridPane);
        initializeFields(gridPane);


        primaryStage.show();

        createBook(gridPane);

        deleteBook(gridPane);

        updateBook(gridPane);

    }

    public void setActionTargetText(String text){ this.actiontarget.setText(text);}

    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane){
        Text sceneTitle = new Text("Welcome!");
        sceneTitle.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 3);
    }
    private void initializeFields(GridPane gridPane){

        HBox hbox= new HBox(10);

        createBookButton= new Button("Create Book");


        deleteBookButton = new Button("Delete Book");

        updateBookButton = new Button("Update Book");


        sellBookButton = new Button("View Report");

        hbox.getChildren().addAll(createBookButton,deleteBookButton,sellBookButton,updateBookButton);
        gridPane.add(hbox,0,2);

        actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        gridPane.add(actiontarget, 5, 1);




        // viewAllBooks(gridPane);

        //buyBook(gridPane);



    }


    public void createBookButtonListener(EventHandler<ActionEvent> createBookButtonListener) {
        createBookButton.setOnAction(createBookButtonListener);

    }
    public void deleteBookButtonListener(EventHandler<ActionEvent> deleteBookButtonListener) {
        deleteBookButton.setOnAction(deleteBookButtonListener);

    }
        public void updateBookButtonListener(EventHandler<ActionEvent> updateBookButtonListener) {
        updateBookButton.setOnAction(updateBookButtonListener);

    }
    public void viewSellBookButtonListener(EventHandler<ActionEvent> sellBookButtonListener) {
        sellBookButton.setOnAction(sellBookButtonListener);

    }

    public void addButtonBook(EventHandler<ActionEvent> sellBookButtonListener)
    {
        addButton.setOnAction(sellBookButtonListener);
    }

    public void addDeleteBook(EventHandler<ActionEvent> deletBookButtonListener)
    {
        deleteButton.setOnAction(deletBookButtonListener);
    }

    public void addUpdateBook(EventHandler<ActionEvent> updateBookButtonListener)
    {
        updateButton.setOnAction(updateBookButtonListener);
    }
    public void createBook(GridPane gridPane) {
        VBox vbox=  new VBox(10);

        authorField = new TextField();
        authorField.setPromptText("Author");


        titleField = new TextField();
        titleField.setPromptText("Title");


        publishedDateField = new TextField();
        publishedDateField.setPromptText("Date(yyyy-MM-dd)");


        priceField = new TextField();
        priceField.setPromptText("Price");


        stockField = new TextField();
        stockField.setPromptText("Stock");

        addButton = new Button("Add");

        updateButton= new Button("Update");
        updateButton.setVisible(false);
        vbox.getChildren().addAll(authorField,titleField,publishedDateField,priceField,stockField,addButton,updateButton);
        gridPane.add(vbox,0,4);


        setCreateBookFalse();


    }
    public Book getBookFromCreateBook()
    {
        String author = authorField.getText();
        String title = titleField.getText();
        String publishedDateText = publishedDateField.getText();
        int price = Integer.parseInt(priceField.getText());
        int stock = Integer.parseInt(stockField.getText());


        LocalDate publishedDate = LocalDate.parse(publishedDateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Book addBook = new BookBuilder()
                .setAuthor(author)
                .setTitle(title)
                .setPublishedDate(publishedDate)
                .setPrice(price)
                .setStock(stock).build();
        return addBook;
    }

    public void setCreateBookVisible()
    {
        authorField.setVisible(true);
        titleField.setVisible(true);
        publishedDateField.setVisible(true);
        priceField.setVisible(true);
        stockField.setVisible(true);
        addButton.setVisible(true);
        updateButton.setVisible(false);
    }

    public void setCreateBookFalse()
    {
        authorField.setVisible(false);
        authorField.clear();
        titleField.setVisible(false);
        titleField.clear();
        publishedDateField.setVisible(false);
        publishedDateField.clear();
        priceField.setVisible(false);
        priceField.clear();
        stockField.setVisible(false);
        stockField.clear();
        addButton.setVisible(false);

    }

    public void deleteBook(GridPane gridPane)
    {
        GridPane gridPane1= new GridPane();
        bookChoiceBox= new ChoiceBox<>();


        List<Book> listOfBooks=bookService.findAll();


        bookChoiceBox.getItems().addAll(listOfBooks);


        gridPane.add(bookChoiceBox, 0 ,10);

        deleteButton= new Button("Delete");

        gridPane.add(deleteButton, 1, 10);
        deleteButton.setVisible(false);

        bookChoiceBox.setVisible(false);

        setDeleteBookVisibleFalse();

    }

    public Book getBookFromDeleteChoiceBox()
    {
        Book deleteBook = bookChoiceBox.getValue();
        return deleteBook;
    }

    public void setDeleteBookVisible()
    {
        bookChoiceBox.setVisible(true);
        deleteButton.setVisible(true);
    }

    public void setDeleteBookVisibleFalse()
    {
        bookChoiceBox.setVisible(false);
        deleteButton.setVisible(false);

    }

    public void updateBook(GridPane gridPane)
    {
      // gridPane.add(updateButton,4,2);

        bookChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //Book updateBook = bookChoiceBox.getValue();
            if (newValue != null) {
                // Update text fields with the newly selected book data
                authorField.setText(newValue.getAuthor());
                titleField.setText(newValue.getTitle());
                publishedDateField.setText(newValue.getPublishedDate().toString());
                priceField.setText(String.valueOf(newValue.getPrice()));
                stockField.setText(String.valueOf(newValue.getStock()));

              //  setActionTargetText("Update successful!");
            }
        });




    }
    public Book getBookFromUpdateBook()
    {
        String author = authorField.getText();
        String title = titleField.getText();
        String publishedDateText = publishedDateField.getText();
        int price = Integer.parseInt(priceField.getText());
        int stock = Integer.parseInt(stockField.getText());
        Book updateBookBefore= bookChoiceBox.getValue();

        LocalDate publishedDate = LocalDate.parse(publishedDateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Book updateBookAfter = new BookBuilder()
                .setId(updateBookBefore.getId())
                .setAuthor(author)
                .setTitle(title)
                .setPublishedDate(publishedDate)
                .setPrice(price)
                .setStock(stock).build();
        return updateBookAfter;
    }
    public void setUpdateBookVisibleTrue()
    {
       // bookTable.setVisible(true);
        authorField.setVisible(true);
        titleField.setVisible(true);
        publishedDateField.setVisible(true);
        priceField.setVisible(true);
        stockField.setVisible(true);
        bookChoiceBox.setVisible(true);
        updateButton.setVisible(true);
    }
    public void setUpdateBookVisibleFalse()
    {

        authorField.setVisible(false);
        authorField.clear();
        titleField.setVisible(false);
        titleField.clear();
        publishedDateField.setVisible(false);
        publishedDateField.clear();
        priceField.setVisible(false);
        priceField.clear();
        stockField.setVisible(false);
        stockField.clear();
        updateButton.setVisible(false);

    }
    public void viewReport() throws IOException {
        List<BookSold> bookSolds = bookSoldService.findAll();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(20,700);
        contentStream.setLeading(14.5f);

        //contentStream.close();
        for(BookSold bookSold : bookSolds)
        {
            //contentStream.beginText();
            contentStream.newLine();
            Book book=bookService.findById(bookSold.getIdBook());

            contentStream.showText("ID: " + bookSold.getId().toString());
            contentStream.showText("        Book ID: " + bookSold.getIdBook());
            contentStream.showText("        Title: " + book.getTitle());
            contentStream.showText("        Quantity: " + bookSold.getNrOfBooksSold());
            // contentStream.close();
        }

        contentStream.close();

        document.save("ReportOfAllTheBooksSold.pdf");
        document.close();
    }


}