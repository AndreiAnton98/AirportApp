package view;

import controller.UserController;
import model.User;

import javax.swing.*;

public class MyAccountPage extends JFrame{
    User user;
    private JButton homeButton;
    private JButton accountButton;
    private JButton logOutButton;
    private JButton backButton;
    private JPanel mainPanel;
    private JTextArea usernameText;
    private JTextArea emailText;
    private JButton changePasswordButton;
    private JTextField usernameField1;
    private JTextField emailField2;
    private JButton changeUsernameButton;
    private JButton changeEmailButton;

    public MyAccountPage(User user){
        this.user = user;
        usernameText.setText(user.getUsername());
        emailText.setText(user.getEmail());
        logOutButton.addActionListener(actionEvent -> {
            new StartPage();
            dispose();
        });
        homeButton.addActionListener(actionEvent -> {
            new MainPage(this.user);
            dispose();
        });
        accountButton.addActionListener(actionEvent -> {
            new MyAccountPage(this.user);
            dispose();
        });
        changeUsernameButton.addActionListener(actionEvent -> changeUsername());
        changeEmailButton.addActionListener(actionEvent -> changeEmail());
        changePasswordButton.addActionListener(actionEvent -> {
            new ChangePasswordPage(user);
            dispose();
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    // todo nu se schimba in timp real ce afiseaza
    private void changeUsername(){
        String newUsername = usernameField1.getText();
        if(newUsername.length() == 0){
            JOptionPane.showMessageDialog(null, "Username must not be empty");
            usernameField1.setText("");
        }else if(UserController.getInstance().findByUsername(newUsername).isPresent()){
            JOptionPane.showMessageDialog(null, "Username in use");
            usernameField1.setText("");
        }else{
            UserController.getInstance().changeUsername(this.user, newUsername);
            JOptionPane.showMessageDialog(null, "Username changed");
            usernameText.setText(this.user.getUsername());
            usernameField1.setText("");
        }
    }
    
    private void changeEmail(){
        String newEmail = emailField2.getText();
        if(UserController.getInstance().findByEmail(newEmail).isPresent()){
            JOptionPane.showMessageDialog(null, "Email in use");
            emailField2.setText("");
        }else if(!(Helper.emailFormatCheck(newEmail))) {
            JOptionPane.showMessageDialog(null, "Email format wrong");
            emailField2.setText("");
        }else{
            UserController.getInstance().changeEmail(this.user, newEmail);
            JOptionPane.showMessageDialog(null, "Email changed");
            emailText.setText(this.user.getEmail());
            emailField2.setText("");
        }
    }
}
