package com.userinterface;
import com.pharmacy.PharmacyPanel;
import com.reception.AddPatient;
import com.admin.AdminPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame {
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton clearButton;
    private JTextField usernameField;
    private JPanel LoginPanel;
    private String staffType;

    public Login(String staffType) {
        this.staffType = staffType;
        setTitle("login");
//        setSize(1000,600);
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // searching login credentials in separate tables for each type
                setVisible(false);
                if (staffType == "admin") {

                    new AdminPanel();
                }
                else if(staffType == "reception") {

                    try {
                        new AddPatient();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
                else if(staffType == "pharmacy"){
                    new PharmacyPanel();
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }


    public JPanel getLoginPanel() {
        return LoginPanel;
    }
}

