package view;

import com.mysql.cj.xdevapi.FindStatementImpl;
import database.JDBConnectionWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import model.Book;
import model.builder.BookBuilder;
import repository.Book.BookRepository;
import repository.Book.BookRepositoryCacheDecorator;
import repository.Book.BookRepositoryMySQL;
import repository.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class CustomerView {

    private TextField customerTextFiled;
    private Button viewBooksButton;
    private Button buyBookButton;

    private Button buyButton;

    private TableView<Book> bookTable;

    private ChoiceBox<Book> bookChoiceBox;

    private final Stage primaryStage;

    private final Scene scene;

    private Text actiontarget;

    private final BookService bookService;

    public CustomerView(Stage primaryStage,BookService bookService)
    {
        this.primaryStage=primaryStage;
        primaryStage.setTitle("Customer");

        this.bookService=bookService;

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);



        initializeSceneTitle(gridPane);

        initializeFields(gridPane);


        primaryStage.show();

    }

    public void setActionTargetText(String text){ this.actiontarget.setText(text);}
    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane){
        Text sceneTitle = new Text("Welcome!");
        sceneTitle.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 2);
    }
    private void initializeFields(GridPane gridPane){

        viewBooksButton = new Button("View Books");
        HBox signInButtonHBox = new HBox(10);
        signInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonHBox.getChildren().add(viewBooksButton);
        gridPane.add(signInButtonHBox, 1, 4);

        buyBookButton = new Button("Buy Book");
        HBox logInButtonHBox = new HBox(10);
        //logInButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        logInButtonHBox.getChildren().add(buyBookButton);
        gridPane.add(logInButtonHBox, 0, 4);


        actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        gridPane.add(actiontarget, 5, 1);


        viewAllBooks(gridPane);

        buyBook(gridPane);



    }
    public void addviewButtonListener(EventHandler<ActionEvent> viewButtonListener) {
        viewBooksButton.setOnAction(viewButtonListener);

    }

    public void addBuyBookButtonListener(EventHandler<ActionEvent> buyBookButtonListener) {
        buyBookButton.setOnAction(buyBookButtonListener);
    }

    public void addBuyButtonListener(EventHandler<ActionEvent> buyBookButtonListener) {
        buyButton.setOnAction(buyBookButtonListener);
    }
    public void viewAllBooks(GridPane gridPane)
    {



        bookTable= new TableView<>();
        bookTable.setEditable(true);
        bookTable.setPrefWidth(400); // Set your preferred width
        bookTable.setPrefHeight(600);

        TableColumn ID= new TableColumn("ID");
        TableColumn title= new TableColumn("Title");
        TableColumn author= new TableColumn("Author");
        TableColumn publishedDate= new TableColumn("Published Date");
        TableColumn price= new TableColumn("Price");
        TableColumn stock = new TableColumn("Stock");
        bookTable.getColumns().addAll(ID,title,author,publishedDate,price,stock);


        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishedDate.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ID.setVisible(false);
        stock.setVisible(false);

        List<Book> listOfBooks=bookService.findAll();
        ObservableList<Book> bookData = FXCollections.observableArrayList();
        bookData.addAll(listOfBooks);

        bookTable.setItems(bookData);

        gridPane.add(bookTable,1,1,1,1);

        bookTable.setVisible(false);

        }

        public void setTableVisibleTrue()
        {
            this.bookTable.setVisible(true);
        }

        public void setTableVisibleFalse()
        {
            this.bookTable.setVisible(false);
        }
        public Book buyBook(GridPane gridPane)
        {
            bookChoiceBox= new ChoiceBox<>();

            List<Book> listOfBooks=bookService.findAll();


            bookChoiceBox.getItems().addAll(listOfBooks);
            Book value= bookChoiceBox.getValue();

            gridPane.add(bookChoiceBox, 1 ,1);

            buyButton= new Button("Buy");


            gridPane.add(buyButton, 4, 1);
            buyButton.setVisible(false);

            bookChoiceBox.setVisible(false);



            return value;
        }
    public void setBookChoiceBoxVisibleTrue()
    {
        this.bookChoiceBox.setVisible(true);
        this.buyButton.setVisible(true);
    }

    public void setBookChoiceBoxVisileFalse()
    {
        this.bookChoiceBox.setVisible(false);
        this.buyButton.setVisible(false);
    }
    public Book getBookFromBuyBook()
    {
        return bookChoiceBox.getValue();

    }

    }


