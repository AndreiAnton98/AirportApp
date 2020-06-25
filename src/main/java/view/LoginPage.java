package view;

import controller.UserController;
import model.User;

import javax.swing.*;

public class LoginPage extends JFrame{
    private JPanel mainPanel;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField1;

    public LoginPage() {
        loginButton.addActionListener(actionEvent -> loginCheck());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void loginCheck(){
        String username = usernameField.getText();
        String password = new String(passwordField1.getPassword());
        if(UserController.getInstance().findByUsername(username).isPresent()) {
            User user = UserController.getInstance().findByUsername(username).get();
            if (user.getPassword().equals(password)){
                new MainPage(user);
                dispose();
            }
        }else if(UserController.getInstance().findByEmail(username).isPresent()){
            User user = UserController.getInstance().findByEmail(username).get();
            if (user.getPassword().equals(password)){
                new MainPage(user);
                dispose();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Wrong");
            usernameField.setText("");
            passwordField1.setText("");
        }
    }
}
