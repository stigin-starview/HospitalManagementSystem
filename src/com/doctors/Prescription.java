package com.doctors;

import com.database.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prescription extends JFrame{
    private JPanel prescriptionPanel;
    private JComboBox patientNameBox;
    private JComboBox medicineNameBox;
    private JCheckBox morningCheckBox;
    private JCheckBox noonCheckBox;
    private JCheckBox nightCheckBox;
    private JRadioButton beforeFoodRadioButton;
    private JRadioButton afterFoodRadioButton;
    private JButton addButton;
    private JTextArea textArea;
    private JButton updatePatientButton;
    private JButton clearButton;
    private JTable medicineDetailsTable;
    private JLabel doctorNameLabel;
    private JLabel patientNameLabel;
    private JLabel patientIdLabel;
    private JLabel patientAgeLabel;
    private JLabel patientAdmitDateLabel;
    private JTable remarksTable;

    private ResultSet patientsResultSet;
    private ResultSet medicineResultSet;
    Database db = new Database();

    public Prescription(String doctorName) throws SQLException {
        setTitle("Dr."+ doctorName);
        setContentPane(prescriptionPanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        doctorNameLabel.setText("Dr."+ doctorName);

        //Getting the assigned patient names.
        patientsResultSet = db.getAssignedPatients(doctorName);

//        ArrayList patients = new ArrayList();
        while(patientsResultSet.next()){
            patientNameBox.addItem(patientsResultSet.getObject(1)+" "+patientsResultSet.getObject(2));
        }

        medicineResultSet = db.getMedicineList();
        while(medicineResultSet.next()) {
            medicineNameBox.addItem(medicineResultSet.getObject(1));
        }

        //Radio button group
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(beforeFoodRadioButton);
        radioGroup.add(afterFoodRadioButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // displaying patient details when pressing jcombo box.
        patientNameBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    patientInformation();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void patientInformation() throws SQLException {
        patientNameLabel.setText("Name: "+ (String) patientNameBox.getSelectedItem());
        // getting patient name and splitting it to get details.
        String patientName = String.valueOf(patientNameBox.getSelectedItem());
        String[] split = patientName.split("\\s+");
        String firstName = split[0];
        String lastName = split[1];
        ResultSet patientDetails = db.getAssignedPatientDetails(firstName, lastName);
        patientDetails.next();
        patientIdLabel.setText("ID: "+patientDetails.getString( 1));
        patientAgeLabel.setText("Age: "+patientDetails.getString(2));
        patientAdmitDateLabel.setText("Admission Date: "+patientDetails.getString(3));

    }
}
