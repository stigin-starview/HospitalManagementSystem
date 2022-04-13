package com.pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.database.*;
import com.userinterface.UserInterface;

public class AddMedicine extends JFrame{
    private JTextField medicineField;
    private JTextField medNumField;
    private JTextField serialNumField;
    private JTextField dateField;
    private JButton addMedicineButton;
    private JButton clearButton;
    private JPanel addMedicinePanel;
    private JButton backButton;
    private JButton homeButton;

    private String name, serialNo, stockDate;
    private int noOfMedicines;

    Database db = new Database();

    public AddMedicine() {
        setTitle("Add medicine");
        setContentPane(addMedicinePanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        dateField.setText(db.getDate());

        addMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = medicineField.getText();
                serialNo = serialNumField.getText();
                noOfMedicines = Integer.parseInt(medNumField.getText());
                stockDate = dateField.getText();

                try {
                    db.addMedicineDb(name,serialNo,noOfMedicines,stockDate);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                //Pop up message;
                JOptionPane.showMessageDialog(null,"Medicine added to the Database!", "Sucessful",1);
                clearMethod();

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMethod();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    db.dbClose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                new PharmacyPanel();
            }

        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    db.dbClose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                new UserInterface();
            }
        });
    }

    private void clearMethod() {
        medicineField.setText("");
        serialNumField.setText("");
        medNumField.setText("");
        dateField.setText("");
    }
}
