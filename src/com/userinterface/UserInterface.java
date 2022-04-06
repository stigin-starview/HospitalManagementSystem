package com.userinterface;

import com.admin.AdminPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {
    private JButton adminButton;
    private JButton receptionButton;
    private JButton doctorButton;
    private JButton pharmacyButton;
    private JPanel welcomePanel;

    public UserInterface() {
        setTitle("Hospital Management System");
//        setSize(1000,600);
        setContentPane(welcomePanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login("admin");
            }
        });

        receptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login("reception");
            }
        });

        pharmacyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login("pharmacy");
            }
        });
        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login("doctor");
            }
        });
    }

    public static void main(String[] args) {
        new UserInterface();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

