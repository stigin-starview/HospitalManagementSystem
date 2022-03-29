package com.login;

import javax.swing.*;
import java.awt.*;

public class welcome extends JFrame {
    private JButton adminButton;
    private JButton receptionButton;
    private JButton doctorButton;
    private JButton pharmacyButton;
    private JPanel welcomePanel;

    public welcome() {
        setTitle("Hospital Management System");
//        setSize(1000,600);
        setContentPane(welcomePanel);
        setMinimumSize(new Dimension(1000,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        welcome test = new welcome();

    }
}

