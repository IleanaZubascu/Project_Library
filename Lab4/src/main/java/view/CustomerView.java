package view;

import com.mysql.cj.xdevapi.FindStatementImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerView {

    private TextField customerTextFiled;
    private Button viewBooksButton;
    private Button buyBookButton;

    public CustomerView(Stage primaryStage)
    {
        primaryStage.setTitle("Customer");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);


        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        primaryStage.show();

    }
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

        buyBookButton = new Button("Buy Books");
        HBox logInButtonHBox = new HBox(10);
        logInButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        logInButtonHBox.getChildren().add(buyBookButton);
        gridPane.add(logInButtonHBox, 0, 4);

    }
    public void addviewButtonListener(EventHandler<ActionEvent> viewButtonListener) {
        viewBooksButton.setOnAction(viewButtonListener);
    }

    public void setBuyBookButtonListener(EventHandler<ActionEvent> buyBookButtonListener) {
        buyBookButton.setOnAction(buyBookButtonListener);
    }

}