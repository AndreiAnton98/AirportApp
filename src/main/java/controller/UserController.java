package controller;

import dao.UserDao;
import model.User;

import java.sql.Connection;
import java.util.Optional;

public class UserController {
    private UserDao userDao;
    private static UserController instance;
    
    private UserController(){
        Connection connection = DatabaseConnection.getInstance();
        userDao = new UserDao(connection);
    }

    public static UserController getInstance(){
        if(instance == null){
            instance = new UserController();
        }
        return instance;
    }
    
    public boolean addUser(User user){
        return userDao.create(user);
    }

    public Optional<User> findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userDao.findByEmail(email);
    }

    public boolean changeUsername(User user, String username){
        return userDao.changeUsername(user, username);
    }
    
    public boolean changeEmail(User user, String email){
        return userDao.changeEmail(user, email);
    }
    
    public boolean changePassword(User user, String password){
        return userDao.changePassword(user, password);
    }
}
