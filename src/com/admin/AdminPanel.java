package com.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame{
    private JButton addEmployeeButton;
    private JButton manageEmployeesButton;
    private JButton adminSettingsButton;
    private JPanel AdminPanel;

    public AdminPanel() {
        setTitle("Administrator Controls");
        setContentPane(AdminPanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddEmployee();

            }
        });

        manageEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ManageEmployees();
            }
        });
    }
}
