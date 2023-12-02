package launcher;

import controller.LoginController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import model.RaportEmployee;
import repository.Book.BookRepository;
import repository.Book.BookRepositoryMySQL;
import repository.BookSold.BookSoldRepository;
import repository.BookSold.BookSoldRepositoryMySQL;
import repository.RaportEmployee.RaportEmployeeRepository;
import repository.RaportEmployee.RaportEmployeeRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.booksold.BookSoldService;
import service.booksold.BookSoldServiceImpl;
import service.raportemployee.RaportEmployeeService;
import service.raportemployee.RaportEmployeeServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;
import view.LoginView;

import java.sql.Connection;
import java.util.ArrayList;

public class ComponentFactory {
    private final LoginView loginView;
    private final LoginController loginController;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final BookRepository bookRepository;

    private final BookSoldRepository bookSoldRepository;

    private final RaportEmployeeRepository raportEmployeeRepository;
    private static ComponentFactory instance;

    private final BookService bookService;

    private final BookSoldService bookSoldService;

    private final RaportEmployeeService raportEmployeeService;


    public static ComponentFactory getInstance(Boolean componentsForTests, Stage stage){
        if (instance == null){
            instance = new ComponentFactory(componentsForTests, stage);
        }

        return instance;
    }

    public ComponentFactory(Boolean componentsForTests, Stage stage){
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceImpl(userRepository, rightsRolesRepository);
        this.loginView = new LoginView(stage);
        this.bookRepository = new BookRepositoryMySQL(connection);
        this.bookService=new BookServiceImpl(this.bookRepository);

        this.bookSoldRepository= new BookSoldRepositoryMySQL(connection);
        this.bookSoldService= new BookSoldServiceImpl(bookSoldRepository);

        this.raportEmployeeRepository= new RaportEmployeeRepositoryMySQL(connection);
        this.raportEmployeeService= new RaportEmployeeServiceImpl(raportEmployeeRepository);


        this.loginController = new LoginController(loginView, authenticationService, bookService,bookSoldService,raportEmployeeService);



    }

    public AuthenticationService getAuthenticationService(){
        return authenticationService;
    }

    public UserRepository getUserRepository(){
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository(){
        return rightsRolesRepository;
    }

    public LoginView getLoginView(){
        return loginView;
    }

    public BookRepository getBookRepository(){
        return bookRepository;
    }

    public LoginController getLoginController(){
        return loginController;
    }

}