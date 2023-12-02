package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.RaportEmployee;
import model.Role;
import model.User;
import model.validator.Notification;
import service.book.BookService;
import service.booksold.BookSoldService;
import service.raportemployee.RaportEmployeeService;
import service.user.AuthenticationService;
import view.AdministratorView;
import view.CustomerView;
import view.EmployeeView;
import view.LoginView;

import java.util.ArrayList;
import java.util.List;

import static database.Constants.Roles.*;

public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;

    private final BookService bookService;

    private final BookSoldService bookSoldService;

    private RaportEmployeeService raportEmployeeService;




    public LoginController(LoginView loginView, AuthenticationService authenticationService, BookService bookService,BookSoldService bookSoldService,RaportEmployeeService raportEmployeeService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.bookService=bookService;
        this.raportEmployeeService=raportEmployeeService;
        this.bookSoldService=bookSoldService;
        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()){
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            }else{
                loginView.setActionTargetText("LogIn Successfull!");
                User user= loginNotification.getResult();
                List<Role> roles= user.getRoles();
                String role= roles.get(0).getRole();

                switch(role){
                    case ADMINISTRATOR : AdministratorController administratorController= new AdministratorController(new AdministratorView(loginView.getPrimaryStage(),authenticationService,raportEmployeeService),authenticationService);break;
                    case EMPLOYEE:  EmployeeController employeeControler= new EmployeeController(new EmployeeView(loginView.getPrimaryStage(),bookService,bookSoldService),bookService,user,raportEmployeeService);break;
                    case CUSTOMER:  CustomerController customerController= new CustomerController(new CustomerView(loginView.getPrimaryStage(),bookService),bookService,bookSoldService);break;

                }
            }

        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");
            }
        }
    }
}