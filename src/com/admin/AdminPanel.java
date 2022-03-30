package com.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame{
    private JButton addStaffButton;
    private JButton removeStaffButton;
    private JButton listStaffButton;
    private JPanel AdminPanel;

    public AdminPanel() {
        setContentPane(AdminPanel);
        setMinimumSize(new Dimension(1000,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
