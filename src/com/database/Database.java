package com.database;

import java.sql.*;


public class Database {
        private Connection db;
        private Statement stat;
        private PreparedStatement preStat;

        private String firstName, lastName, email, username, password,
                        department, employeeType, Date, status, phoneNumber, id;
        private int age;


        // Establishing the connection with mysql server
        public void connectionInit() throws SQLException {
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalDatabase","root","571612");
            this.db = db;
        }

        // Add employee method
        public void addEmployeeDb(String firstName, String lastName, int age,
                                  String phoneNumber, String email, String id,
                                  String department, String employeeType, String date,
                                  String status, String username, String password) throws SQLException {

            connectionInit();

            // Adding the employee details.
            String query = " INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            preStat = db.prepareStatement(query);
            this.preStat = preStat;
            preStat.setString(1, id);
            preStat.setString(2, firstName);
            preStat.setString(3, lastName);
            preStat.setInt(4, age);
            preStat.setString(5, email);
            preStat.setString(6, phoneNumber);
            preStat.setString(7, department);
            preStat.setString(8, employeeType);
            preStat.setString(9, date);
            preStat.setString(10, username);
            preStat.setString(11, password);
            preStat.setString(12, status);

            preStat.executeUpdate();
            preStat.close();
            db.close();

        }

        //Getting the list of all employees
        public ResultSet listEmployeesDb() throws SQLException {
            connectionInit();
            stat = db.createStatement();
            String query = "SELECT * FROM employees";
            ResultSet employeesResultSet = stat.executeQuery(query);
            ResultSetMetaData employeesMeta = employeesResultSet.getMetaData();
            return employeesResultSet;

        }


        //Updating employee details
        public void updateEmployee(String firstName, String lastName, int age,
                                   String phoneNumber, String email, String id,
                                   String department, String employeeType, String date,
                                   String status, String username, String password) throws SQLException {

        }

        public void dbClose() throws SQLException {

            stat.close();
            db.close();
        }

}

