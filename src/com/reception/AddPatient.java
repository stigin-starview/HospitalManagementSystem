package com.reception;

import com.database.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPatient extends JFrame{
    private JPanel addPatient;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ageField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField dateField;
    private JComboBox doctorBox;
    private JButton addPatientButton;
    private JButton clearButton;
    private JTextField idField;

    private String firstName, lastName, email, date, phoneNumber, id, doctor, medicine, remark;
    private int age;

    Database db = new Database();
    private ResultSet doctorsResultSet, patientIdResultSet;
    private ResultSetMetaData resultSetMetaData, idResultSetMeta;

    public AddPatient() throws SQLException {
        // sql codes added into the constructor itself
        // not like manageEmployees class------ check and change manageEmployees class.
        setTitle("Add Patients");
        setContentPane(addPatient);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //retrieving the details of the doctors

        doctorsResultSet = db.getDoctorsDb();
//        resultSetMetaData = doctorsResultSet.getMetaData();
//        int cols = resultSetMetaData.getColumnCount();
//        int i = 0;
        ArrayList doctorName = new ArrayList();

        //adding individual list to arraylist
        while(doctorsResultSet.next()) {

            doctorName.add(doctorsResultSet.getString(1) +" "+ doctorsResultSet.getString(2));

        }
        //itterate through each object to get the value and add to the box.
        for(Object m:doctorName) {
            doctorBox.addItem(m);
        }
        getPatientNumber();



        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // getting entered details
                id = idField.getText();
                firstName = firstNameField.getText();
                lastName = lastNameField.getText();
                email = emailField.getText();
                date = dateField.getText();
                doctor = doctorBox.getSelectedItem().toString();
                phoneNumber = phoneNumberField.getText();
                age = ageField.getColumns();
                System.out.println(age);
//                medicine = "NILL";
//                remark = "NILL";


                try {
                    db.addPatientDB(firstName, lastName, age, phoneNumber, email, id, date,
                            doctor, medicine, remark);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                //Pop up message;
                JOptionPane.showMessageDialog(null,"Patient Details created successfully!", "Sucessful",1);

                clearMethod();
                try {
                    getPatientNumber();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMethod();
            }
        });
    }

    private void getPatientNumber() throws SQLException {
        //get patient number.
        patientIdResultSet = db.getPatientIdDb();
        idResultSetMeta = patientIdResultSet.getMetaData();
        String idNumber = "000" + patientIdResultSet.getRow();
        idField.setText(idNumber);
    }

    private void clearMethod() {
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
        idField.setText("");
        dateField.setText("");
    }

    }
