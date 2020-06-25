package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/airport?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";
    private static Connection instance;

    private DatabaseConnection(){

    }
    public static Connection getInstance(){
        if(instance == null){
            try {
                instance = DriverManager.getConnection(url,user,password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }
}
