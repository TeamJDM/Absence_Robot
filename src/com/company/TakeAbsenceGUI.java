/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dimos
 */
public class TakeAbsenceGUI extends JFrame{
    
    //private boolean DEBUG = false;
    //private StudentList studentList;
    private Absence absence;
    private JPanel contentPane;
    private JPanel tablePane;
    private JCheckBox[] checkBoxes;
    
    public TakeAbsenceGUI(ArrayList<String> names){
       
        Object[][] rowData = new Object[names.size()][2];
        String[] col = {"Name", "Absence"};
        checkBoxes = new JCheckBox[names.size()];
        for (int i = 0; i < names.size(); i++) {
            rowData[i][0] = names.get(i);
            rowData[i][1] = (Object) checkBoxes[i];
        }
        
        
        contentPane = (JPanel) this.getContentPane();
        tablePane = new JPanel();
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(rowData, col);
        table.setModel(tableModel);
        //tableModel.setColumnIdentifiers(new String[] {"Name", "Absence"});
        
//        for (String str: names) {
//            tableModel.addRow(new Object[] {str, Boolean.FALSE});
//        }
        tablePane.setLayout(new BorderLayout());
        
        this.setTitle("Take Absences");
        contentPane.add(tablePane, BorderLayout.CENTER);
        tablePane.add(table, BorderLayout.CENTER);
    }
    

}

