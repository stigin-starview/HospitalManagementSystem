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
    private JTextArea writeRemarktextArea;
    private JButton updatePatientButton;
    private JButton clearButton;
    private JTable medicineDetailsTable;
    private JLabel doctorNameLabel;
    private JLabel patientNameLabel;
    private JLabel patientIdLabel;
    private JLabel patientAgeLabel;
    private JLabel patientAdmitDateLabel;
    private JTextArea medicineTextArea;
    private JTextArea remarksTextArea;
    private JTable remarksTable;

    private ResultSet patientsResultSet;
    private ResultSet medicineResultSet;
    Database db = new Database();

    private int medicineAddCount = 0;
    private String currentPatientID = null;

    public Prescription(String doctorName) throws SQLException {
        setTitle("Dr."+ doctorName);
        setContentPane(prescriptionPanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        doctorNameLabel.setText("Dr."+ doctorName);
        medicineTextArea.append(db.getDate()+"\n");
        remarksTextArea.append(db.getDate()+"\n");

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
                    medicineTextArea.append(db.getDate()+"\n");
                    remarksTextArea.append(db.getDate()+"\n");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Updating medicine.
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                medicineAddCount++;
                String medicineName=null, beforeOrAfterFood =null;
                medicineName = medicineAddCount+". "+String.valueOf(medicineNameBox.getSelectedItem());
                if(beforeFoodRadioButton.isSelected()) {
                    beforeOrAfterFood = "Before food";
                }
                else if(afterFoodRadioButton.isSelected()) {
                    beforeOrAfterFood = "After food";
                }
                String medicineInstruction = medicineName+"\nconsume: "+beforeOrAfterFood+"\nTime:";
                if(morningCheckBox.isSelected()) {
                    medicineInstruction = medicineInstruction+" morning";
                }
                if(noonCheckBox.isSelected()) {
                    medicineInstruction = medicineInstruction+" noon";
                }
                if(nightCheckBox.isSelected()) {
                    medicineInstruction = medicineInstruction+" night";
                }


                medicineTextArea.append(medicineInstruction+"\n");
                morningCheckBox.setSelected(false);
                noonCheckBox.setSelected(false);
                nightCheckBox.setSelected(false);

            }
        });

        //clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        // update patient details to database.
        updatePatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    db.addPatientPrescription(currentPatientID, medicineTextArea.getText(),remarksTextArea.getText()+writeRemarktextArea.getText());
                    db.dbClose();
                    //Pop up message;
                    JOptionPane.showMessageDialog(null,"Prescription Added Successfully", "Sucessful",1);
                    clear();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    //getting information of the selected patient.
    private void patientInformation() throws SQLException {
        patientNameLabel.setText("Name: "+ (String) patientNameBox.getSelectedItem());
        // getting patient name and splitting it to get details.
        String patientName = String.valueOf(patientNameBox.getSelectedItem());
        String[] split = patientName.split("\\s+");
        String firstName = split[0];
        String lastName = split[1];
        ResultSet patientDetailsResultSet = db.getAssignedPatientDetailsDb(firstName, lastName);
        patientDetailsResultSet.next();
        patientIdLabel.setText("ID: "+patientDetailsResultSet.getString( 1));
        patientAgeLabel.setText("Age: "+patientDetailsResultSet.getString(2));
        patientAdmitDateLabel.setText("Admission Date: "+patientDetailsResultSet.getString(3));
        medicineTextArea.setText(patientDetailsResultSet.getString(4)+"\n");
        remarksTextArea.setText(patientDetailsResultSet.getString(5)+"\n");

        //assigning current patient id to global variable
        this.currentPatientID = patientDetailsResultSet.getString( 1);
    }
    private void clear() {
        writeRemarktextArea.setText("");
        morningCheckBox.setSelected(false);
        noonCheckBox.setSelected(false);
        nightCheckBox.setSelected(false);
        medicineTextArea.setText("");
        remarksTextArea.setText("");
    }
}
