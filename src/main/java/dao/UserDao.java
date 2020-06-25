package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {

    private PreparedStatement createStatement;
    private PreparedStatement findAllByIdStatement;
    private PreparedStatement findAllByUsernameStatement;
    private PreparedStatement findAllByEmailStatement;
    private PreparedStatement changeUsernameStatement;
    private PreparedStatement changeEmailStatement;
    private PreparedStatement changePasswordStatement;

    public UserDao(Connection connection){

        try {
            createStatement = connection.prepareStatement("INSERT INTO user VALUES (null, ?, ?, ?)");
            findAllByIdStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            findAllByUsernameStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            findAllByEmailStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            changeUsernameStatement = connection.prepareStatement("UPDATE user SET username = ? where username = ?");
            changeEmailStatement = connection.prepareStatement("UPDATE user SET email = ? where username = ?");
            changePasswordStatement = connection.prepareStatement("UPDATE user SET password = ? where username = ?");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean create(User user){
        try {
            createStatement.setString(1, user.getUsername());
            createStatement.setString(2, user.getPassword());
            createStatement.setString(3, user.getEmail());
            return createStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Optional<User> findByUsername(String username){
        try {
            findAllByUsernameStatement.setString(1, username);
            ResultSet rs = findAllByUsernameStatement.executeQuery();
            if(rs.next()){
                return Optional.of(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email){
        try {
            findAllByEmailStatement.setString(1, email);
            ResultSet rs = findAllByEmailStatement.executeQuery();
            if(rs.next()){
                return Optional.of(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean changeUsername(User user, String username){
        try {
            changeUsernameStatement.setString(1, username);
            changeUsernameStatement.setString(2, user.getUsername());
            return changeUsernameStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    
    public boolean changeEmail(User user, String email){
        try {
            changeEmailStatement.setString(1, email);
            changeEmailStatement.setString(2, user.getUsername());
            return changeEmailStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean changePassword(User user, String password){
        try {
            changePasswordStatement.setString(1, password);
            changePasswordStatement.setString(2, user.getUsername());
            return changePasswordStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
