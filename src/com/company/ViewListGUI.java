package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ViewListGUI extends JFrame {


    private StudentList studentList;
    private UnitList unitList;


    private ArrayList<JCheckBox> checkBoxes;

    private JPanel contentPane;
    private JPanel tablePane;
    private JPanel infoPanel;
    private JPanel buttonPane;


    private JComboBox unitsBox;
    private JButton bAdd;
    private JButton bDelete;
    private JButton bDone;

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
        //infoPanel.add(unitsBox);
        unitsBox.setSelectedIndex(-1);
        unitsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click_actionPerformed(e);
            }
        });

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
                info.setLayout(new GridLayout(3, 3));
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
                try {
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                tablePane.removeAll();

                try {
                    FileInputStream saveFile = new FileInputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectInputStream save = new ObjectInputStream(saveFile);
                    studentList = (StudentList) save.readObject();
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
                for (Student s : studentList.getArrayOfStudents()) {
                    checkBoxes.add(new JCheckBox(String.valueOf(s.getId()), false));
                    tablePane.add(checkBoxes.get(i));
                    tablePane.add(new JLabel(s.getName()));
                    tablePane.add(new JLabel(String.valueOf(s.checkAbsenceLimit(studentList.getUnit().getUnitName()))));
                    tablePane.add(new JLabel(String.valueOf(studentList.getUnit().getAbsencesPermitted())));

                    i++;
                }
                buttonPane.add(bAdd);
                buttonPane.add(bDelete);

                buttonPane.repaint();
                contentPane.add(tablePane, BorderLayout.CENTER);
                tablePane.revalidate();
                tablePane.repaint();


            }
        });


        bDelete = new JButton("Delete");
        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s;
                for (JCheckBox jch : checkBoxes) {
                    if (jch.isSelected()) {

                        s = studentList.getStudentById(Integer.parseInt(jch.getText()));
                        studentList.deleteStudent(s);

                    }
                }

                try {
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                tablePane.removeAll();

                try {
                    FileInputStream saveFile = new FileInputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectInputStream save = new ObjectInputStream(saveFile);
                    studentList = (StudentList) save.readObject();
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
                for (Student si : studentList.getArrayOfStudents()) {
                    checkBoxes.add(new JCheckBox(String.valueOf(si.getId()), false));
                    tablePane.add(checkBoxes.get(i));
                    tablePane.add(new JLabel(si.getName()));
                    tablePane.add(new JLabel(String.valueOf(si.checkAbsenceLimit(studentList.getUnit().getUnitName()))));
                    tablePane.add(new JLabel(String.valueOf(studentList.getUnit().getAbsencesPermitted())));

                    i++;
                }
                buttonPane.add(bAdd);
                buttonPane.add(bDelete);

                buttonPane.repaint();
                contentPane.add(tablePane, BorderLayout.CENTER);
                tablePane.revalidate();
                tablePane.repaint();
            }
        });


        infoPanel.add(unitsBox);
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

    private void click_actionPerformed(ActionEvent e) {

        tablePane.removeAll();

        try {
            FileInputStream saveFile = new FileInputStream("saveFile_" + this.unitsBox.getSelectedItem().toString() + ".dat");
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
        for (Student s : studentList.getArrayOfStudents()) {
            checkBoxes.add(new JCheckBox(String.valueOf(s.getId()), false));
            tablePane.add(checkBoxes.get(i));
            tablePane.add(new JLabel(s.getName()));
            tablePane.add(new JLabel(String.valueOf(s.checkAbsenceLimit(studentList.getUnit().getUnitName()))));
            tablePane.add(new JLabel(String.valueOf(studentList.getUnit().getAbsencesPermitted())));

            i++;
        }
        buttonPane.add(bAdd);
        buttonPane.add(bDelete);

        buttonPane.repaint();
        contentPane.add(tablePane, BorderLayout.CENTER);
        tablePane.revalidate();
        tablePane.repaint();
    }

}