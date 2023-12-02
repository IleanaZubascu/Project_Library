package repository.user;

import model.User;
import model.validator.Notification;

import java.util.*;

public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    void removeAll();

    boolean existsByUsername(String username);

    void deleteUser(Long id);

    void setEmployee(Long id);

    List<User> findAllEmployee();

    List<User> findAllCustomer();
}