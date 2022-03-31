package com.admin;

import com.database.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ManageEmployees extends JFrame{
    private JTable employeesTable;
    private JPanel manageEmployeesPanel;
    private JButton updateButton;
    private JButton printButton;
    private ResultSet employeesResultSet;
    private ResultSetMetaData resultSetMetaData;


    private String firstName, lastName, email, username, password,
            department, employeeType, date, status, phoneNumber, id,age;

    Database dat = new Database();
    public ManageEmployees() {
        setTitle("Manage Employees");
        setContentPane(manageEmployeesPanel);
        setMinimumSize(new Dimension(1000,600));
        setLocation(500,200);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        try {
            manageEmployeesTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void manageEmployeesTable() throws SQLException {


       employeesResultSet = dat.listEmployeesDb();
       resultSetMetaData = employeesResultSet.getMetaData();
       int cols = resultSetMetaData.getColumnCount();
       DefaultTableModel tableModel = (DefaultTableModel) employeesTable.getModel();
       String[] colName = new String[cols];


        for(int i=0; i < cols; i++) {
           colName[i] = resultSetMetaData.getColumnName(i + 1);
//           System.out.println(colName[i]);
       }
       tableModel.setColumnIdentifiers(colName);
//        tableModel.addRow(colName);



        while(employeesResultSet.next()) {
            id = employeesResultSet.getString(1);
            firstName = employeesResultSet.getString(2);
            lastName = employeesResultSet.getString(3);
            age = employeesResultSet.getString(4);
            email = employeesResultSet.getString(5);
            phoneNumber = employeesResultSet.getString(6);
            department = employeesResultSet.getString(7);
            employeeType = employeesResultSet.getString(8);
            date = employeesResultSet.getString(9);
            username = employeesResultSet.getString(10);
            password = employeesResultSet.getString(11);
            status = employeesResultSet.getString(12);

            String[] row = {id, firstName, lastName, age, email, phoneNumber,
                            department, employeeType, date, username, password, status};
            tableModel.addRow(row);
        }// while loop ends.

        dat.dbClose();






    }
}
