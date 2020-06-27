package view;

import controller.AuditController;
import controller.UserController;
import model.Audit;
import model.User;

import javax.swing.*;
import java.time.LocalDateTime;

public class ChangePasswordPage extends JFrame{
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton changePasswordButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private User user;
    public ChangePasswordPage(User user){
        this.user = user;
        String password1 = new String(passwordField1.getPassword());
        String password2 = new String(passwordField2.getPassword());
        cancelButton.addActionListener(actionEvent -> {
            new MyAccountPage(this.user);
            dispose();
        });
        changePasswordButton.addActionListener(actionEvent ->  changePassword());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void changePassword(){
        String password1 = new String(passwordField1.getPassword());
        String password2 = new String(passwordField2.getPassword());
        if(!(Helper.passwordFormatCheck(password1))){
            JOptionPane.showMessageDialog(null, "Password format error");
            passwordField1.setText("");
            passwordField2.setText("");
        }else if(!(password1.equals(password2))){
            JOptionPane.showMessageDialog(null, "Passwords don't match");
            passwordField1.setText("");
            passwordField2.setText("");
        }else{
            UserController.getInstance().changePassword(this.user, password1);
            JOptionPane.showMessageDialog(null, "Password changed");
            AuditController.getInstance().addAudit(new Audit(1, user.getId(), "Password change", LocalDateTime.now().toString()),user);
            new MyAccountPage(this.user);
            dispose();
        }
    }
}
