package com.userinterface;
import com.doctors.Prescription;
import com.pharmacy.PharmacyPanel;
import com.reception.AddPatient;
import com.admin.AdminPanel;
import com.database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton clearButton;
    private JTextField usernameField;
    private JPanel LoginPanel;
    private String staffType;

    // username and password for testing
    private String masterUsername = "root";
    private String masterPassword = "password";

    private String username;
    private String password;

    Database db = new Database();
    ResultSet resultSet;

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


//                username = "'"+usernameField.getText()+"'";
//                password = "'"+String.valueOf(passwordField.getPassword())+"'";
                username = usernameField.getText();
                String password1 = String.valueOf(passwordField.getPassword());
               password = password1;
                System.out.println(username + " \npass:" + password);
                if (staffType == "admin") {
                    setVisible(false);
                    new AdminPanel();
                }
                else if(staffType == "reception") {

                    try {
                        setVisible(false);
                        new AddPatient();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
                else if(staffType == "pharmacy"){
                    setVisible(false);
                    new PharmacyPanel();
                }
                else if(staffType == "doctor"){
                    System.out.println("inside docotr else if");
                    try {
                        System.out.println("inside try");
                        resultSet = db.doctorPasswordDb(username,password);
                        System.out.println(resultSet.getRow());
                        if (resultSet == null) {
                            System.out.println("inside tru- if");
                            JOptionPane.showMessageDialog(null,"Invalid username/Password.", "Login Failed",2);
                            clearMethod();
                            db.dbClose();
                        }
                        else{
                            System.out.println("inside try else");
                            setVisible(false);
                            new Prescription();

                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMethod();
            }
        });
    }

    private void clearMethod() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public JPanel getLoginPanel() {
        return LoginPanel;
    }
}

