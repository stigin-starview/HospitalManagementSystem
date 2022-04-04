package com.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public AddEmployee(){
        setTitle("Add Employee");
        setContentPane(addEmployeePanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


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
