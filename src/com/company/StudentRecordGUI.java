package com.company;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;
//import java.nio.files.Paths;
import java.nio.file.Path;
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
        this.setTitle("Search for Student");


        detailsPane = new JPanel();
        detailsPane.setLayout(new FlowLayout());
        lName = new JLabel("Student Name");
        detailsPane.add(lName);
        tName = new JTextField();
        tName.setColumns(30);
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
    public void bCancel_actionPerformed(ActionEvent e){
        JFrame unitGui  = new StudentRecordGUI();
        unitGui.pack();
        unitGui.setLocationRelativeTo(null);
        unitGui.setVisible(true);

    }
    public void bSearch_actionPerformed(ActionEvent e){
        JScrollPane showPane = new JScrollPane();
        JPanel infoStudentPne = new JPanel();
        infoStudentPne.setLayout(new GridLayout(2,2));

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Unit Name");
        model.addColumn("Student Absences");
        model.addColumn("Absences Permitted");
        String name = null;
        int id = 0;

        for (StudentList sl: studentLists){
            for (Student s: sl.getArrayOfStudents()){
                if (tName.getText().equals(s.getName())){
                    //firstId = s.getId();
                    name = s.getName();
                    id = s.getId();

                    model.addRow(new Object[]{sl.getUnit().getUnitName(),s.checkAbsenceLimit(sl.getUnit().getUnitName()),sl.getUnit().getAbsencesPermitted()});
                }
            }
        }
        infoStudentPne.add(new JLabel("ID: "));
        infoStudentPne.add(new JLabel(String.valueOf(id)));
        infoStudentPne.add(new JLabel("Name: "));
        infoStudentPne.add(new JLabel(name));
        contentPane.remove(detailsPane);
        contentPane.add(infoStudentPne, BorderLayout.NORTH);
        showPane.setViewportView(table);
        contentPane.add(showPane, BorderLayout.CENTER);
        this.setSize(600, 400);


    }

    private boolean isForAll() {

        ArrayList<Student> studentsSearched = new ArrayList<Student>();
        for (StudentList sl: studentLists){
            for (Student s: sl.getArrayOfStudents()) {
                if (tName.getText().equals(s.getName())) {
                    studentsSearched.add(s);
                }
            }

        }
        int[] studentPos = new int[studentsSearched.size()];
        for (int i=0; i < studentsSearched.size(); i++){
            if (studentsSearched.get(i).getId() != studentsSearched.get(i+1).getId()){

                studentPos[i] = i;
            }
        }
        for (Student s2:studentsSearched){
            if (s2.getId() == s2.getId()){
                return true;
            }
        }
        return false;
    }

}
    


