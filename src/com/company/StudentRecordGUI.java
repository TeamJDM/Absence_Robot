package com.company;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.files.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StudentRecordGUI extends JFrame{

    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;
      
    private JLabel lName;

    private JTextField tName;

    private JButton bSearch;
    private JButton bBack;

    private ArrayList<StudentList> studentLists;

    public StudentRecordGUI(){

        studentLists = new ArrayList<StudentList>();
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
            	mg.setLocationRelativeTo(null);
            	mg.setVisible(true);
            	dispose();
            }
        });
        buttonPane.add(bBack);
        
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(detailsPane, BorderLayout.CENTER);

        File f = new File(Paths.get(".").toAbsolutePath().normalize().toString());
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("saveFile_") && name.endsWith(".dat");
            }
        });
        for (File fi: matchingFiles){
            try {
                FileInputStream saveFile = new FileInputStream(fi);
                ObjectInputStream save = new ObjectInputStream(saveFile);
                this.studentLists.add((StudentList) save.readObject());
                save.close();
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void bSearch_actionPerformed(ActionEvent e){
        JPanel showPane = new JPanel();
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Unit Name");
        model.addColumn("Student ID");
        model.addColumn("Student Name");
        model.addColumn("Student Absences");
        model.addColumn("Absences Permitted");
        for (StudentList sl: studentLists){
            for (Student s: sl.getArrayOfStudents()){
                if (tName.getText().equals(s.getName())){

//                    showPane.add(new JLabel(sl.getUnit().getUnitName()));
//                    showPane.add(new JLabel(String.valueOf(s.getId())));
//                    showPane.add(new JLabel(s.getName()));
//                    showPane.add(new JLabel(String.valueOf(s.checkAbsenceLimit(sl.getUnit().getUnitName()))));
//                    showPane.add(new JLabel(String.valueOf(sl.getUnit().getAbsencesPermitted())));
                    model.addRow(new Object[]{sl.getUnit().getUnitName(),String.valueOf(s.getId()),s.getName(),s.checkAbsenceLimit(sl.getUnit().getUnitName()),sl.getUnit().getAbsencesPermitted()});
                }
            }
        }
        contentPane.remove(detailsPane);
        contentPane.add(table, BorderLayout.CENTER);
        contentPane.repaint();
    }    
}
