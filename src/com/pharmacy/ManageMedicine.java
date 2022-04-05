package com.pharmacy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.database.*;

public class ManageMedicine extends JFrame{
    private JTable medicineTable;
    private JPanel manageMedicinePanel;
    private ResultSet medicinesResultSet;
    private ResultSetMetaData medicinesResultSetMd;

    private String name, serialNo, noOfMedicines, stockDate;

    Database db = new Database();
    public ManageMedicine() {
        setTitle("Medicine List");
        setContentPane(manageMedicinePanel);
        setMinimumSize(new Dimension(1100,600));
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Getting the details form database
        try {
            getMedicineList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getMedicineList() throws SQLException {
        medicinesResultSet = db.getMedicineList();
        medicinesResultSetMd = medicinesResultSet.getMetaData();
        // creating table model.
        DefaultTableModel tableModel = (DefaultTableModel) medicineTable.getModel();
        int col = medicinesResultSetMd.getColumnCount();
        String[] colName = new String[col];
        String tempString;

        for(int i=0; i < col; i++) {
            tempString = medicinesResultSetMd.getColumnName(i + 1);
            colName[i] = tempString.toUpperCase();

        }
        tableModel.setColumnIdentifiers(colName);

        while(medicinesResultSet.next()) {
            name = medicinesResultSet.getString(1);
            serialNo = medicinesResultSet.getString(2);
            noOfMedicines = String.valueOf(medicinesResultSet.getInt(3));
            stockDate = medicinesResultSet.getString(4);
            String[] row = {name, serialNo, noOfMedicines, stockDate};
            tableModel.addRow(row);

        }
        db.dbClose();

    }
}
