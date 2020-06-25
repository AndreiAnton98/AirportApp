package view;

import javax.swing.*;

public class StartPage extends JFrame{
    private JPanel mainPanel;
    private JButton registerButton;
    private JButton loginButton;

    public StartPage(){
        
        loginButton.addActionListener(actionEvent -> {
            new LoginPage();
            dispose();
        });
        
        registerButton.addActionListener(actionEvent -> {
            new RegisterPage();
            dispose();
        });
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
