package com.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.*;

public class AddEmployee extends JFrame {
    private JPanel addEmployeePanel;
    private JTextField firstNameField;
    private JTextField secondNameField;
    private JTextField ageField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField idField;
    private JTextField dateField;
    private JComboBox statusBox;
    private JButton addEmployeeButton;
    private JButton clearButton;
    private JTextField userNameField;
    private JTextField passwordField;
    private JComboBox typeBox;
    private JComboBox departmentBox;

    private String firstName, lastName, email, username, password,
            department, employeeType, date, status, phoneNumber, id;
    private int age;

    Database dat = new Database();
    ResultSet employeeCountResultSet;

    public AddEmployee(){
        setTitle("Add Employee");
        setContentPane(addEmployeePanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        dateField.setText(dat.getDate());


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clearMethod();
            }
        });
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = idField.getText();
                firstName = firstNameField.getText();
                lastName = secondNameField.getText();
                email = emailField.getText();
                username = userNameField.getText();
                password = passwordField.getText();
                department = departmentBox.getSelectedItem().toString();
                employeeType = typeBox.getSelectedItem().toString();
                date = dateField.getText();
                status = statusBox.getSelectedItem().toString();
                phoneNumber = phoneField.getText();
                age = Integer.parseInt(ageField.getText());


                try {
                    dat.addEmployeeDb(firstName, lastName, age, phoneNumber, email, id, department,
                                        employeeType, date, status, username, password);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                //Pop up message;
                JOptionPane.showMessageDialog(null,"Employee Added to the Database.", "Sucessful",1);

                clearMethod();



            }
        });

    /*    departmentBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depName = String.valueOf(departmentBox.getSelectedItem());
//                System.out.println(test);
                String num;
                String idNum;
                if (depName == "Doctor") {
                    try {
                        employeeCountResultSet = dat.getEmployeeCountDb("'Doctor'");
                        employeeCountResultSet.next();
                        num = employeeCountResultSet.getString(1);
                        idNum = "DOC_00"+num;
                        idField.setText(idNum);
                        dat.dbClose();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                }
                else if(depName == "Administrator") {
                    try {
                        employeeCountResultSet = dat.getEmployeeCountDb("'Administrator'");
                        employeeCountResultSet.next();
                        num = employeeCountResultSet.getString(1);
                        idNum = "ADMIN_00"+num;
                        idField.setText(idNum);
                        dat.dbClose();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
                else if(depName == "Pharmacy") {
                    try {
                        employeeCountResultSet = dat.getEmployeeCountDb("'Pharmacy'");
                        employeeCountResultSet.next();
                        num = employeeCountResultSet.getString(1);
                        idNum = "PHAR_00"+num;
                        idField.setText(idNum);
                        dat.dbClose();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
                else
                {
                    try {
                        employeeCountResultSet = dat.getEmployeeCountDb("'Reception'");
                        employeeCountResultSet.next();
                        num = employeeCountResultSet.getString(1);
                        idNum = "RECEP_00"+num;
                        idField.setText(idNum);
                        dat.dbClose();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }); */
    }
        private void clearMethod() {
        firstNameField.setText("");
        secondNameField.setText("");
        ageField.setText("");
        phoneField.setText("");
        emailField.setText("");
        idField.setText("");
        dateField.setText("");
        userNameField.setText("");
        passwordField.setText("");
    }
}
