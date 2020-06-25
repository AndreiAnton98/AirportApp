package view;

import model.User;

import javax.swing.*;

public class MainPage extends JFrame{
    private JButton homeButton;
    private JButton accountButton;
    private JButton logOutButton;
    private JButton backButton;
    private JTable table1;
    private JButton addFlightButton;
    private JPanel mainPanel;
    private User user;

    public MainPage(User user){
        this.user = user;
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
