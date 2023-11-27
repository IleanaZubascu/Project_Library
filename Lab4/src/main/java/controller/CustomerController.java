package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CustomerView;

public class CustomerController {

    private final CustomerView customerView;

    public CustomerController(CustomerView customerView) {
        this.customerView = customerView;
    }

    private class viewBooksButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {



        }
    }
}