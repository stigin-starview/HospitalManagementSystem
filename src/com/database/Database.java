package com.database;

import java.sql.*;

// packages for getting the current date.
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class Database {
        private Connection db = null;
        private Statement stat = null;
        private PreparedStatement preStat = null;

        private String firstName, lastName, email, username, password,
                        department, employeeType, Date, status, phoneNumber, id;
        private int age;


        // Establishing the connection with mysql server
        public void connectionInit() throws SQLException {
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalDatabase","root","571612");
            this.db = db;
        }

        public ResultSet employeePasswordDb(String staffType) throws SQLException {
            connectionInit();
            String query = "SELECT id, firstname, lastname, username, password FROM employees WHERE department = ?";
            preStat = db.prepareStatement(query);
            preStat.setString(1, staffType);
            ResultSet employee = preStat.executeQuery();
            return employee;

        }

        // Add employee method
        public void addEmployeeDb(String firstName, String lastName, int age,
                                  String phoneNumber, String email, String id,
                                  String department, String employeeType, String date,
                                  String status, String username, String password) throws SQLException {

            connectionInit();

            // Adding the employee details.
            String query = " INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            // check below 2 lines.
            preStat = db.prepareStatement(query);
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
            return employeesResultSet;

        }


        //Updating employee details
        public void updateEmployee(String firstName, String lastName, int age,
                                   String phoneNumber, String email, String id,
                                   String department, String employeeType, String date,
                                   String status, String username, String password) throws SQLException {

        }

        // Get the names of all the doctors.
        public ResultSet getDoctorsDb() throws SQLException {
            connectionInit();
            stat = db.createStatement();
            String query = "SELECT  firstname, lastname FROM employees where department = 'Doctor' ";
            ResultSet doctorsResultSet = stat.executeQuery(query);
            return doctorsResultSet;
        }

    // Adding patient details
    public void addPatientDB(String firstName, String lastName, int age,
                             String phoneNumber, String email, String id,
                             String date, String doctor, String medicine, String remark) throws SQLException {
        connectionInit();

        // Adding the patients details.
        String query = " INSERT INTO patients VALUES (?,?,?,?,?,?,?,?,?,?)";
        preStat = db.prepareStatement(query);
        preStat.setString(1, firstName);
        preStat.setString(2, lastName);
        preStat.setInt(3, age);
        preStat.setString(4, email);
        preStat.setString(5, phoneNumber);
        preStat.setString(6, date);
        preStat.setString(7, id);
        preStat.setString(8, doctor);
        preStat.setString(9, medicine);
        preStat.setString(10, remark);
        preStat.executeUpdate();
        dbClose();
    }


    //get id from database
    public ResultSet getPatientIdDb() throws SQLException {
        connectionInit();
        String query = "SELECT id FROM patients";
        ResultSet patientIdResultSet = stat.executeQuery(query);
        return patientIdResultSet;
    }


    // Adding medicine
    public void addMedicineDb(String name, String serialNo, int noOfMedicines, String stockDate) throws SQLException {

        String query = "INSERT INTO pharmacy VALUES (?,?,?,?)";
        connectionInit();
        preStat = db.prepareStatement(query);

        preStat.setString(1, name);
        preStat.setString(2, serialNo);
        preStat.setInt(3, noOfMedicines);
        preStat.setString(4, stockDate);
        preStat.executeUpdate();
        dbClose();

    }

    // Retrieving medicine details
    public ResultSet getMedicineList() throws SQLException {
        connectionInit();
        stat = db.createStatement();
        String query = "SELECT * FROM pharmacy";
        ResultSet medicinesResultSet = stat.executeQuery(query);
        return medicinesResultSet;
    }

    // Getting assigned patient list for the selected doctor.

    public ResultSet getAssignedPatients(String doctorName) throws SQLException {
        String query = "SELECT firstname, lastname FROM patients WHERE doctor = ?";
        connectionInit();
        preStat = db.prepareStatement(query);
        preStat.setString(1, doctorName);
        ResultSet patients = preStat.executeQuery();
        return patients;
    }

    public ResultSet getAssignedPatientDetailsDb(String firstName, String lastName) throws SQLException {
        String query = "SELECT id, age, admitdate, medicine, remark FROM patients WHERE firstname = ? AND lastname = ?";
        connectionInit();
        preStat = db.prepareStatement(query);
        preStat.setString(1, firstName);
        preStat.setString(2, lastName);
        ResultSet patientDetails = preStat.executeQuery();
        return patientDetails;
    }

    public void addPatientPrescription(String id, String medicine, String remarks) throws SQLException {
        String query = "UPDATE patients SET medicine = ?, remark = ? WHERE id = ?";
        connectionInit();
        preStat = db.prepareStatement(query);
        preStat.setString(1, medicine);
        preStat.setString(2, remarks);
        preStat.setString(3, id);
        preStat.executeUpdate();

    }

        // close all the opened databases.
        public void dbClose() throws SQLException {
            if( db != null) {
                if (this.stat == null && this.preStat == null) {
                    db.close();
                } else if (this.stat == null) {
                    preStat.close();
                    db.close();
                } else if (this.preStat == null) {
                    stat.close();
                    db.close();

                } else {
                    stat.close();
                    preStat.close();
                    db.close();
                }
            }
        }

    //get the current system date.
    public String getDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar obj = Calendar.getInstance();
        String date = formatter.format(obj.getTime());
        return date;
    }
}

