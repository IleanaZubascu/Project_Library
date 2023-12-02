package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import model.BookSold;
import model.RaportEmployee;
import model.User;
import model.validator.Notification;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.raportemployee.RaportEmployeeService;
import service.user.AuthenticationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorView {

    private TextField customerTextFiled;
    private Button saveUserButton;
    private Button setEmployeeButton;
    private Button deleteEmployeeButton;
    private Button viewEmployeeActivityButton;

    private HBox hbox;



    private final Stage primaryStage;

    private final Scene scene;

    private Text actiontarget;

    private final AuthenticationService userService;

    private ChoiceBox<User> customerChoiceBox;
    private ChoiceBox<User> employeeChoiceBox;

    private Button saveButton;


    private Button editButton;

    private Button deleteButton;

    TextField usernameField;
    PasswordField passwordField;

    RaportEmployeeService raportEmployeeService;


    public AdministratorView(Stage primaryStage, AuthenticationService userService,RaportEmployeeService raportEmployeeService)
    {
        this.raportEmployeeService=raportEmployeeService;

        this.primaryStage=primaryStage;
        primaryStage.setTitle("Admin");

        this.userService=userService;

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);

        initializeSceneTitle(gridPane);
        initializeFields(gridPane);

        setEmployee(gridPane);

        deleteEmployee(gridPane);
        saveUser(gridPane);

        primaryStage.show();

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

        hbox= new HBox();
        hbox.setSpacing(10);
        deleteEmployeeButton= new Button("Delete Employee");

        saveUserButton= new Button("Save User");

        setEmployeeButton= new Button("Make employee");

        viewEmployeeActivityButton= new Button("View Employee");

        hbox.getChildren().addAll(deleteEmployeeButton,viewEmployeeActivityButton,
                                    saveUserButton,setEmployeeButton);

        gridPane.add(hbox,0,2);

        actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        gridPane.add(actiontarget, 5, 1);




    }
    public void setDeleteEmployeeButton(EventHandler<ActionEvent> viewButtonListener) {
        deleteEmployeeButton.setOnAction(viewButtonListener);

    }
    public void setViewEmployeeActivityButton(EventHandler<ActionEvent> buyBookButtonListener) {
        viewEmployeeActivityButton.setOnAction(buyBookButtonListener);
    }

    public void setSaveUserButton(EventHandler<ActionEvent> buyBookButtonListener) {
        saveUserButton.setOnAction(buyBookButtonListener);
    }

    public void setSetEmployeeButton(EventHandler<ActionEvent> buyBookButtonListener) {
        setEmployeeButton.setOnAction(buyBookButtonListener);
    }

    public void addEditButton(EventHandler<ActionEvent> editButtonListener)
    {
        editButton.setOnAction(editButtonListener);
    }

    public void addDeleteButton(EventHandler<ActionEvent> deleteButtonListener)
    {
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addSaveButton(EventHandler<ActionEvent> saveButtonListener)
    {
        saveButton.setOnAction(saveButtonListener);
    }
    public void setEmployee(GridPane gridPane)
    {
        customerChoiceBox= new ChoiceBox<>();
        List<User> listOfCustomer=userService.findAllCustomer();
        customerChoiceBox.getItems().addAll(listOfCustomer);


        gridPane.add(customerChoiceBox, 0 ,10);

        editButton= new Button("Update");


        gridPane.add(editButton, 1, 10);


        editButton.setVisible(false);
        customerChoiceBox.setVisible(false);

    }

    public User getCustumerFromSetEmployee()
    {
        User value= customerChoiceBox.getValue();
        return value;
    }


    public void deleteEmployee(GridPane gridPane)
    {
        employeeChoiceBox= new ChoiceBox<>();
        List<User> listOfEmployee=userService.findAllEmployee();
        employeeChoiceBox.getItems().addAll(listOfEmployee);


        gridPane.add(employeeChoiceBox, 0 ,11);

        deleteButton= new Button("Delete");


        gridPane.add(deleteButton, 1, 11);


        deleteButton.setVisible(false);
        employeeChoiceBox.setVisible(false);
    }

    public User getEmployeeChoiceBoxFromDeleteEmployee()
    {
        User value= employeeChoiceBox.getValue();
        return value;

    }


    public void setDeleteEmployeeVisibleTrue()
    {
        deleteButton.setVisible(true);
        employeeChoiceBox.setVisible(true);
    }
    public void setDeleteEmployeeVisibleFalse()
    {
        deleteButton.setVisible(false);
        employeeChoiceBox.setVisible(false);
    }

    public void setEmployeeVisibleTrue()
    {
        editButton.setVisible(true);
        customerChoiceBox.setVisible(true);
    }
    public void setEmployeeVisibleFalse()
    {
        editButton.setVisible(false);
        customerChoiceBox.setVisible(false);
    }

    public void saveUser(GridPane gridPane)
    {
        VBox vbox= new VBox();
        vbox.setSpacing(5);
        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Passworrd");

        saveButton= new Button("Save");
        vbox.getChildren().addAll(usernameField,passwordField,saveButton);
        gridPane.add(vbox,0,4);


        usernameField.setVisible(false);
        passwordField.setVisible(false);
        saveButton.setVisible(false);
    }

    public List<String> getUsernameAndPassworgFromSaveUser()
    {
        String username= usernameField.getText();
        String password=passwordField.getText();
        List<String> user= new ArrayList<>();
        user.add(username);
        user.add(password);
        return user;
    }

    public void setSaveUserVisibleTrue()
    {
        usernameField.setVisible(true);
        passwordField.setVisible(true);
        saveButton.setVisible(true);
    }

    public void setSaveUserVisibleFalse()
    {
        usernameField.setVisible(false);
        passwordField.setVisible(false);
        saveButton.setVisible(false);
    }

    public void viewReportEmployee() throws IOException {
        List<RaportEmployee> raportEmployeeActivity =raportEmployeeService.findall();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(20,700);
        contentStream.setLeading(14.5f);


        for(RaportEmployee raport : raportEmployeeActivity)
        {
            //contentStream.beginText();
            contentStream.newLine();
            contentStream.showText(raport.getText());
        }

        contentStream.close();

        document.save("RaportAllEmployee.pdf");
        document.close();
    }


}


