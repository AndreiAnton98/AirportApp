package view;

import controller.AuditController;
import model.Audit;
import model.User;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public class UserHistoryPage extends JFrame{
    private JPanel mainPanel;
    private JButton homeButton;
    private JButton myAccountButton;
    private JButton userHistoryButton;
    private JButton logOutButton;
    private JButton backButton;
    private JList list1;
    private User user;
    private DefaultListModel<Audit> model;


    public UserHistoryPage(User user){
        this.user = user;
        model = new DefaultListModel<>();
        list1.setModel(model);
        afisAudit();
        logOutButton.addActionListener(actionEvent -> {
            new StartPage();
            dispose();
            AuditController.getInstance().addAudit(new Audit(1, user.getId(), "Logout", LocalDateTime.now().toString()),user);
        });
        homeButton.addActionListener(actionEvent -> {
            new MainPage(this.user);
            dispose();
            AuditController.getInstance().addAudit(new Audit(1, user.getId(), "Main Page", LocalDateTime.now().toString()),user);
        });
        myAccountButton.addActionListener(actionEvent -> {
            new MyAccountPage(this.user);
            dispose();
            AuditController.getInstance().addAudit(new Audit(1, user.getId(), "Account Page", LocalDateTime.now().toString()),user);
        });
        userHistoryButton.addActionListener(actionEvent -> {
            new UserHistoryPage(this.user);
            dispose();
            AuditController.getInstance().addAudit(new Audit(1, user.getId(), "History Page", LocalDateTime.now().toString()),user);
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void afisAudit(){
        List<Audit> list = AuditController.getInstance().getAudit(user);
        model.clear();
        list.forEach(a -> model.addElement(a));
    }
}
