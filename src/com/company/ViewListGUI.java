package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ViewListGUI extends JFrame{
    

    //private Absence absence;
    private StudentList studentList;
    private UnitList unitList;
    //private ArrayList<Student> absentStudents;

    private ArrayList<JCheckBox> checkBoxes;

    private JPanel contentPane;
    private JPanel tablePane;
    private JPanel infoPanel;
    private JPanel buttonPane;

    //private ArrayList<JCheckBox> checkBoxes;
    private JComboBox unitsBox;
    private JButton bAdd;
    private JButton bDelete;
    private JButton bEdit;
    private JButton bDone;
    private JButton bLoad;

    private Unit selectedUnit;

    public ViewListGUI() {

        checkBoxes = new ArrayList<JCheckBox>();

        try {
            FileInputStream saveFile = new FileInputStream("units.dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.unitList = (UnitList) save.readObject();
            save.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        contentPane = (JPanel) this.getContentPane();
        contentPane.setPreferredSize(new Dimension(400, 200));
        tablePane = new JPanel();
        this.setTitle("View List");

        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        unitsBox = new JComboBox();
        for (Unit u : unitList.getUnits()) {
            unitsBox.addItem(u.getUnitName());
        }
        infoPanel.add(unitsBox);
        unitsBox.setSelectedIndex(-1);

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bAdd = new JButton("Add");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel inputDialog = new JPanel();
                JPanel info = new JPanel();
                JPanel buttons = new JPanel();
                JLabel lName = new JLabel("Name");
                JTextField tName = new JTextField();
                JLabel lTel = new JLabel("Telephone Number");
                JTextField tTel = new JTextField();
                JLabel lEmail = new JLabel("Email");
                JTextField tEmail = new JTextField();
                info.setLayout(new GridLayout(3,3));
                info.add(lName);
                info.add(tName);
                info.add(lTel);
                info.add(tTel);
                info.add(lEmail);
                info.add(tEmail);
                inputDialog.add(info, BorderLayout.CENTER);

                int result = JOptionPane.showConfirmDialog(null, inputDialog,
                        "Please Enter New Students Info", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    studentList.addStudent(new Student(tName.getText(), Integer.parseInt(tTel.getText()), tEmail.getText()));
                }
                try{
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString()+ ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                }
                catch (FileNotFoundException ex){
                    ex.printStackTrace();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }


            }
        });
        buttonPane.add(bAdd);

        bDelete = new JButton("Delete");
        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s;
                for (JCheckBox jch: checkBoxes){
                    if (jch.isSelected()){
                        try {
                            s = studentList.getStudentById(Integer.parseInt(jch.getText()));
                            studentList.deleteStudent(s);
                        } catch (NoStudentException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                try{
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString()+ ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                }
                catch (FileNotFoundException ex){
                    ex.printStackTrace();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        buttonPane.add(bDelete);

        bEdit = new JButton("Edit");
        bEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student selected = null;
                for (JCheckBox ch: checkBoxes){
                    if (ch.isSelected()){
                        try {
                            selected = studentList.getStudentById(Integer.parseInt(ch.getText()));
                        } catch (NoStudentException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    }
                }

                JPanel inputDialog = new JPanel();
                JPanel info = new JPanel();
                //JPanel buttons = new JPanel();
                JLabel lName = new JLabel("Name");
                JTextField tName = new JTextField(selected.getName());
                JLabel lTel = new JLabel("Telephone Number");
                JTextField tTel = new JTextField(selected.getTel());
                JLabel lEmail = new JLabel("Email");
                JTextField tEmail = new JTextField(selected.getEmail());
                info.setLayout(new GridLayout(3,3));
                info.add(lName);
                info.add(tName);
                info.add(lTel);
                info.add(tTel);
                info.add(lEmail);
                info.add(tEmail);
                inputDialog.add(info, BorderLayout.CENTER);

                int result = JOptionPane.showConfirmDialog(null, inputDialog,
                        "Please Enter New Students Info", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {

                    studentList.addStudent(new Student(tName.getText(), Integer.parseInt(tTel.getText()), tEmail.getText()));
                }
                studentList.deleteStudent(selected);
                try{
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString()+ ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                }
                catch (FileNotFoundException ex){
                    ex.printStackTrace();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        buttonPane.add(bEdit);

        bLoad = new JButton("Load the list first");
        bLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
           	     unitsBox.getSelectedIndex();
           	     bLoad_actionPerformed(e);
           	}
           	catch (Exception wi2){
           		JOptionPane.showMessageDialog(null, "You need to select the specific unit", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
           	}
            	
            }
        });
        buttonPane.add(bLoad);

        bDone = new JButton("Done");
        bDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI mg = new MainGUI();
                mg.setLocationRelativeTo(null);
                mg.pack();
                mg.setVisible(true);
            }
        });
        buttonPane.add(bDone);

        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(infoPanel, BorderLayout.NORTH);
    }
    
    private void bLoad_actionPerformed(ActionEvent e) {
        try {
            FileInputStream saveFile = new FileInputStream("saveFile_" + this.unitsBox.getSelectedItem().toString()+ ".dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.studentList = (StudentList) save.readObject();
            save.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        tablePane.setLayout(new GridLayout(studentList.getNameList().size(), 4));
        int i = 0;
        for (Student s: studentList.getArrayOfStudents()){
            checkBoxes.add(new JCheckBox(String.valueOf(s.getId()), false));
            tablePane.add(checkBoxes.get(i));
            tablePane.add(new JLabel(s.getName()));
            tablePane.add(new JLabel(String.valueOf(s.checkAbsenceLimit(studentList.getUnit().getUnitName()))));
            tablePane.add(new JLabel(String.valueOf(studentList.getUnit().getAbsencesPermitted())));

            i++;
        }

        contentPane.add(tablePane, BorderLayout.CENTER);
        tablePane.revalidate();
        tablePane.repaint();

    }
}
