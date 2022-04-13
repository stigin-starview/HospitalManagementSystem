package com.pharmacy;

import com.userinterface.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PharmacyPanel extends JFrame{
    private JButton addMedicineButton;
    private JPanel pharmacyPanel;
    private JButton listMedicineButton;
    private JButton homeButton;

    public PharmacyPanel() {
        setTitle("Pharmacy");
        setContentPane(pharmacyPanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        addMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddMedicine();
            }
        });
        listMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ManageMedicine();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UserInterface();
            }
        });
    }
}
