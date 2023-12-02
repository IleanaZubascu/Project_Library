package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.RaportEmployee;
import model.User;
import model.builder.BookBuilder;
import model.builder.RaportEmployeeBuilder;
import service.book.BookService;
import service.raportemployee.RaportEmployeeService;
import service.raportemployee.RaportEmployeeServiceImpl;
import view.EmployeeView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeController {

    private final EmployeeView employeeView;

    private final BookService bookService;

    private final RaportEmployeeService raportEmployee;


    User user;

    public EmployeeController(EmployeeView emplyeeView, BookService bookService, User user, RaportEmployeeService raportEmployee) {
        this.employeeView=emplyeeView;
        this.bookService=bookService;
        this.raportEmployee=raportEmployee;

        this.employeeView.createBookButtonListener(new EmployeeController.createBookButton());
        this.employeeView.deleteBookButtonListener(new EmployeeController.deleteBookButton());
        this.employeeView.updateBookButtonListener(new EmployeeController.updateBookButton());
        this.employeeView.viewSellBookButtonListener(new EmployeeController.viewReportSellBook());
        this.employeeView.addButtonBook(new EmployeeController.addBookButton());
        this.employeeView.addDeleteBook(new EmployeeController.deleteBookButtonController());
        this.employeeView.addUpdateBook(new EmployeeController.updateBookButtonController());
        this.user=user;

    }
    private class viewReportSellBook implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            try {
                employeeView.viewReport();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private class updateBookButtonController implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            try {

               Book updateBookAfter= employeeView.getBookFromUpdateBook();
                bookService.updateBook(updateBookAfter);
                updateBookAfter.toStringView();
                String description= "Employee with username: "+user.getUsername()+" update book with title: "+updateBookAfter.getTitle();
//                RaportEmployee raportEmployee1= new RaportEmployeeBuilder()
//                                                    .setText(description)
//                                                    .build();
                raportEmployee.save(description);
                employeeView.setActionTargetText("Update successfull!");
            }catch (Exception ex)
            {
                employeeView.setActionTargetText("Wrong Data!");
            }

        }
    }
    private class deleteBookButtonController implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            try {
                Book deleteBook = employeeView.getBookFromDeleteChoiceBox();
                bookService.deleteBook(deleteBook);
                String text="Employee with username: "+user.getUsername()+" delete book with title: "+deleteBook.getTitle();
//                RaportEmployee raportEmployee1= new RaportEmployeeBuilder()
//                        .setText(text)
//                        .build();
                raportEmployee.save(text);

                employeeView.setActionTargetText("Delete successfull!");
            }catch (Exception ex)
            {
                employeeView.setActionTargetText("Something goes wrong!");
            }

        }
    }
    private class addBookButton implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {

            try {
                Book addBook= employeeView.getBookFromCreateBook();
                bookService.save(addBook);
                String text="Employee with username: "+ user.getUsername() + " create book with title: "+addBook.getTitle();
//                RaportEmployee raportEmployee1= new RaportEmployeeBuilder()
//                        .setText(text)
//                        .build();
                raportEmployee.save(text);
                employeeView.setActionTargetText("Create successfull!");
            }catch (Exception exception)
            {
                employeeView.setActionTargetText("Wrong data!");
            }


        }

        }


    private class createBookButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            employeeView.setDeleteBookVisibleFalse();
            employeeView.setUpdateBookVisibleFalse();
            employeeView.setCreateBookVisible();
            //employeeView.setActionTargetText("Create successfull");


        }

    }

    private class deleteBookButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            employeeView.setCreateBookFalse();
            employeeView.setUpdateBookVisibleFalse();
            employeeView.setDeleteBookVisible();
            //employeeView.setActionTargetText("Create successfull");


        }

    }
    private class updateBookButton implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            employeeView.setCreateBookFalse();
            employeeView.setDeleteBookVisibleFalse();
            employeeView.setUpdateBookVisibleTrue();
        }
    }


}
