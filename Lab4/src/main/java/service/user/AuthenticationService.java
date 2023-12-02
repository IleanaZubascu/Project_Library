package service.user;

import model.User;
import model.validator.Notification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface AuthenticationService {
    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password);

    boolean logout(User user);

    public void deleteUser(Long id);


    public void setEmployee(Long id);

    public List<User> findAllEmployee();
    public List<User> findAllCustomer();
}