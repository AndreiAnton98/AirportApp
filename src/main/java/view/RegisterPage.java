package view;

import controller.UserController;
import model.User;

import javax.swing.*;

public class RegisterPage extends JFrame{
    private JPanel mainPanel;
    private JButton registerButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;

    public RegisterPage() {
        registerButton.addActionListener(actionEvent -> addUser());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addUser(){
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String email = emailField.getText();

        if(username.length() == 0){
            JOptionPane.showMessageDialog(null, "Username field must not be null");
            resetFields();
        }else if(UserController.getInstance().findByUsername(username).isPresent()){
            JOptionPane.showMessageDialog(null, "Username is already taken");
            resetFields();
        }else if(!(Helper.passwordFormatCheck(password))){
            JOptionPane.showMessageDialog(null, "Password format wrong");
            resetFields();
        }else if(!(password.equals(confirmPassword))) {
            JOptionPane.showMessageDialog(null, "Passwords must match");
            resetFields();
        }else if(UserController.getInstance().findByEmail(email).isPresent()){
            JOptionPane.showMessageDialog(null, "Email in use");
            resetFields();
        }else if(!(Helper.emailFormatCheck(email))){
            JOptionPane.showMessageDialog(null, "Email format wrong");
            resetFields();
        }else{
            UserController.getInstance().addUser(new User(0, username, password, email));
            new LoginPage();
            dispose();
        }
    }

    private void resetFields(){
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        emailField.setText("");
    }
}
