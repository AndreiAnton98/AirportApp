package view;

import controller.AuditController;
import controller.FlightController;
import model.Audit;
import model.Flight;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.time.LocalDateTime;
import java.util.List;

public class MainPage extends JFrame{
    private JButton homeButton;
    private JButton accountButton;
    private JButton logOutButton;
    private JButton backButton;
    private JTable table1;
    private JButton addFlightButton;
    private JPanel mainPanel;
    private JPanel panelTable;
    private JButton userHistoryButton;
    private User user;
    private List<Flight> flights;
    private DefaultTableModel model;

    public MainPage(User user){
        this.user = user;
        this.flights = FlightController.getInstance().getFlights(user);
        model = new DefaultTableModel();
        setTable();
        addFlightButton.addActionListener(actionEvent -> new AddFlightPage(user));
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
        accountButton.addActionListener(actionEvent -> {
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

    private void setTable() {
        model.setColumnIdentifiers(new Object[]{"From", "To", "Departure Time", "Arrival Time", "Days", "Price"});
        table1.setModel(model);
        table1.setRowHeight(40);


        for(Flight flight : flights){
            model.addRow(new Object[]{flight.getDeparture(), flight.getArrival(), flight.getDeparture_time(), flight.getArrival_time(), flight.getDays(), flight.getPrice()});
        }
    }


}
