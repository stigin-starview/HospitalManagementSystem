package com.pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PharmacyPanel extends JFrame{
    private JButton addMedicineButton;
    private JPanel pharmacyPanel;
    private JButton listMedicineButton;

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
    }
}
