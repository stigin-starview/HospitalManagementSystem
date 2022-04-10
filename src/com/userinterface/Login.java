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
import java.util.Arrays;

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

                username = usernameField.getText();
                // securing the password.
                char[] password = passwordField.getPassword();
                String employeeName = null;
                boolean employeeFound = false;
                boolean masterAccess = false;

                //checking if master username and password is used
                if (username.equals(masterUsername) && Arrays.equals(masterPassword.toCharArray(), password)) {
                    masterAccess = true;
                }
                else {
                        try {
                            resultSet = db.employeePasswordDb(staffType);
                            while (resultSet.next()) {

                                // converting the resultset password into char array and comparing the fields
                                if (username.equals(resultSet.getString(4)) && Arrays.equals(resultSet.getString(5).toCharArray(), password)) {
                                    employeeName = resultSet.getString(2) + " " + resultSet.getString(3);
                                    employeeFound = true;
                                    break;
                                }
                            }
                            if (employeeFound == false) {
                                JOptionPane.showMessageDialog(null, "Invalid username/Password.", "Login Failed", 2);
                                clearMethod();
                                db.dbClose();

                            }

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }

                if(employeeFound == true || masterAccess == true) {
                    setVisible(false);

                    if (staffType == "admin") {

                        new AdminPanel();

                    } else if (staffType == "reception") {

                        try {
                            new AddPatient();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    } else if (staffType == "pharmacy") {

                        new PharmacyPanel();

                    } else if (staffType == "doctor") {
                        try {
                            new Prescription(employeeName);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
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

