package com.company;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class StudentRecordGUI extends JFrame{


    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;
    

    
    private JLabel lName;

    private JTextField tName;

    private JButton bSearch;
    private JButton bBack;

    public StudentRecordGUI(){

        contentPane = (JPanel)this.getContentPane();
        contentPane.setPreferredSize(new Dimension(350,150));
        this.setTitle("Search for Student");

        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(1,1));
        lName = new JLabel("Student Name");
        detailsPane.add(lName);
        tName = new JTextField();
        detailsPane.add(tName);

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
       
        bSearch = new JButton("Search");
        buttonPane.add(bSearch);
        bSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bSearch_actionPerformed(e);
            }
        });
        
        bBack = new JButton("Back");
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainGUI mg = new MainGUI();
            	mg.pack();
            	mg.setVisible(true);
            	dispose();
            }
        });
        buttonPane.add(bBack);
        
        
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(detailsPane, BorderLayout.CENTER);


    }
    public void bCancel_actionPerformed(ActionEvent e){
        JFrame unitGui  = new StudentRecordGUI();
        unitGui.pack();
        unitGui.setVisible(true);
    }
    public void bSearch_actionPerformed(ActionEvent e){
        JFrame unitGui  = new StudentRecordGUI();
        unitGui.pack();
        unitGui.setVisible(true);
    }
    
}
