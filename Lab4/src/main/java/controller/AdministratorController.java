package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.RaportEmployee;
import model.User;
import model.validator.Notification;
import service.user.AuthenticationService;
import view.AdministratorView;
import view.CustomerView;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class AdministratorController {

    private final AdministratorView administratorView;

    private final AuthenticationService authenticationService;


    public AdministratorController(AdministratorView administratorView,AuthenticationService authenticationService) {
        this.administratorView = administratorView;
        this.authenticationService=authenticationService;

        this.administratorView.setSetEmployeeButton(new AdministratorController.setEmployeeButton());
        this.administratorView.setDeleteEmployeeButton(new AdministratorController.setDeleteEmployeeButton());
        this.administratorView.setSaveUserButton(new AdministratorController.setSaveUserButton());
        this.administratorView.setViewEmployeeActivityButton(new AdministratorController.viewEmployee());
        this.administratorView.addEditButton(new AdministratorController.setEditButton());
        this.administratorView.addDeleteButton(new AdministratorController.setDeleteButton());
        this.administratorView.addSaveButton(new AdministratorController.setSaveButton());
    }
    public class viewEmployee implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            try {
                administratorView.viewReportEmployee();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private class setSaveButton implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            List<String> user= administratorView.getUsernameAndPassworgFromSaveUser();

            Notification<Boolean> registerNotification = authenticationService.register(user.get(0), user.get(1));

            if (registerNotification.hasErrors()) {
                administratorView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                administratorView.setActionTargetText("Register successful!");
            }
        }
    }
    private class setDeleteButton implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            try {
                User value= administratorView.getEmployeeChoiceBoxFromDeleteEmployee();
                authenticationService.deleteUser(value.getId());
                administratorView.setActionTargetText("Delete successfull!");
            }catch (Exception ex)
            {
                administratorView.setActionTargetText("Something goes wrong on delete Customer!");
            }
        }
    }
    private class setEditButton implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            try {
                User value= administratorView.getCustumerFromSetEmployee();
                authenticationService.setEmployee(value.getId());
                administratorView.setActionTargetText("Update successfull!");
            }catch (Exception ex)
            {
                administratorView.setActionTargetText("Something goes wrong!");
            }
        }
    }
    private class setEmployeeButton implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            administratorView.setSaveUserVisibleFalse();
            administratorView.setDeleteEmployeeVisibleFalse();
            administratorView.setEmployeeVisibleTrue();

        }
    }
    private class setDeleteEmployeeButton implements  EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            administratorView.setSaveUserVisibleFalse();
            administratorView.setEmployeeVisibleFalse();
            administratorView.setDeleteEmployeeVisibleTrue();
        }
    }
    private class setSaveUserButton implements  EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            administratorView.setDeleteEmployeeVisibleFalse();
            administratorView.setEmployeeVisibleFalse();
            administratorView.setSaveUserVisibleTrue();
        }
    }
}
