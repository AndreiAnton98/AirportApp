package view;

import controller.FlightController;
import model.Flight;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddFlightPage extends JFrame{
    private JPanel mainPanel;
    private JTextField fromField;
    private JTextField toField;
    private JTextField departureTimeField;
    private JTextField durationField;
    private JCheckBox mondaycheckBox1;
    private JCheckBox tuesdaycheckBox2;
    private JCheckBox wednesdayCheckBox;
    private JCheckBox thursdayCheckBox;
    private JCheckBox fridayCheckBox;
    private JCheckBox saturdayCheckBox;
    private JCheckBox sundayCheckBox;
    private JTextField priceField;
    private JButton cancelButton;
    private JButton addFlightButton;
    private User user;
    private List<String> days;

    public AddFlightPage(User user){
        this.user = user;
        days = new ArrayList<>();
        mondaycheckBox1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox cb = (JCheckBox) actionEvent.getSource();
                if(cb.isSelected()){
                    days.add("Monday");
                }
            }
        });
        tuesdaycheckBox2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox cb = (JCheckBox) actionEvent.getSource();
                if(cb.isSelected()){
                    days.add("Tuesday");
                }
            }
        });
        wednesdayCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox cb = (JCheckBox) actionEvent.getSource();
                if(cb.isSelected()){
                    days.add("Wednesday");
                }
            }
        });
        thursdayCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox cb = (JCheckBox) actionEvent.getSource();
                if(cb.isSelected()){
                    days.add("Thursday");
                }
            }
        });
        fridayCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox cb = (JCheckBox) actionEvent.getSource();
                if(cb.isSelected()){
                    days.add("Friday");
                }
            }
        });
        saturdayCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox cb = (JCheckBox) actionEvent.getSource();
                if(cb.isSelected()){
                    days.add("Saturay");
                }
            }
        });
        sundayCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox cb = (JCheckBox) actionEvent.getSource();
                if(cb.isSelected()){
                    days.add("Sunday");
                }
            }
        });
        cancelButton.addActionListener(actionEvent -> {
            new MainPage(this.user);
            dispose();
        });
        addFlightButton.addActionListener(actionEvent -> addFlight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addFlight(){
        String from = fromField.getText();
        String to = toField.getText();
        String departure_time = departureTimeField.getText();
        String duration = durationField.getText();
        String price = priceField.getText();
        String fromTo = new String(from + to);
        if(from.length() < 4 || to.length() < 4){
            JOptionPane.showMessageDialog(null, "Minumum 3 letters for from and to");
            resetFields();
        }else if(Helper.flightExists(user, fromTo)){
            JOptionPane.showMessageDialog(null, "Flight already exists");
            resetFields();
        }else if(!(Helper.checkTimeFormat(departure_time)) || !(Helper.checkTimeFormat(duration))){
            JOptionPane.showMessageDialog(null, "Time format wrong");
            resetFields();
        }else if(!(Helper.checkPrice(price))){
            JOptionPane.showMessageDialog(null, "Incorrect price");
            resetFields();
        }else{
            String daysString = String.join(", ", days);
            String arrival_time = Helper.addTimes(departure_time, duration);
            FlightController.getInstance().addFlight(new Flight(0, user.getId(), from, to, departure_time, arrival_time, daysString, Integer.parseInt(price)), user);
            new MainPage(user);
            dispose();
        }
    }

    private void resetFields(){
        fromField.setText("");
        toField.setText("");
        departureTimeField.setText("");
        durationField.setText("");
        priceField.setText("");
        mondaycheckBox1.setSelected(false);
        tuesdaycheckBox2.setSelected(false);
        wednesdayCheckBox.setSelected(false);
        thursdayCheckBox.setSelected(false);
        fridayCheckBox.setSelected(false);
        saturdayCheckBox.setSelected(false);
        sundayCheckBox.setSelected(false);
    }
}
