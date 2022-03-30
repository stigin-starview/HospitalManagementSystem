package com.database;

import java.sql.*;


public class Database {
        private Connection db;

        private String firstName, lastName, email, username, password,
                        department, employeeType, Date, status, phoneNumber, id;
        private int age;


//        public Database() throws SQLException {
//            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalDatabase","root","571612");
//            this.db = db;
//        }

        public void addEmployeeDb(String firstName, String lastName, int age,
                                  String phoneNumber, String email, String id,
                                  String department, String employeeType, String date,
                                  String status, String username, String password) throws SQLException {
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalDatabase","root","571612");
            this.db = db;
            String query = " INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stat = db.prepareStatement(query);
            stat.setString(1, id);
            stat.setString(2, firstName);
            stat.setString(3, lastName);
            stat.setInt(4, age);
            stat.setString(5, email);
            stat.setString(6, phoneNumber);
            stat.setString(7, department);
            stat.setString(8, employeeType);
            stat.setString(9, date);
            stat.setString(10, username);
            stat.setString(11, password);
            stat.setString(12, status);

            stat.executeUpdate();
            stat.close();
            db.close();

        }

}

