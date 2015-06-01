package com.company;

import javax.sql.rowset.serial.SerialArray;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.JarFile;

public class UnitGUI extends JFrame{

    //private JPanel contentPaneRoot;
    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;
    private JPanel studentGUIPane;

    private JComboBox jComboBox;
    private JLabel lYear;
    private JTextField tfYear;
    private JButton bOk;
    private JButton bBack;
    private JButton bAddUnit;
    private JButton bDeleteUnit;

    private UnitList units;
    private Unit selectedUnit;

    public UnitGUI() {


        try {
            FileInputStream saveFile = new FileInputStream("units.dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.units = (UnitList) save.readObject();
            save.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        contentPane = (JPanel)this.getContentPane();
        contentPane.setPreferredSize(new Dimension(200,150));
        this.setTitle("Choose Unit to create a Student List");

        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(2,2));
        lYear = new JLabel("Year:");
        detailsPane.add(lYear);
        tfYear = new JTextField();
        detailsPane.add(tfYear);
        jComboBox = new JComboBox();
        for (Unit uni: units.getUnits()){
            jComboBox.addItem(uni.getUnitName());
        }
        detailsPane.add(jComboBox);
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bOk = new JButton("OK");
        bOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bOk_actionPerformed(e);
            }
        });
        buttonPane.add(bOk);
        
        bBack = new JButton("Back");
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	MainGUI mg = new MainGUI();
            	mg.pack();
            	mg.setVisible(true);
            	
            }
        });
        buttonPane.add(bBack);
        bAddUnit = new JButton("Add Unit");
        bAddUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame addFrame = new AddUnitGui();
                addFrame.pack();
                addFrame.setVisible(true);

            }
        });
        buttonPane.add(bAddUnit);
        bDeleteUnit = new JButton("Delete Unit");
        bDeleteUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String unitNameDelete = JOptionPane.showInputDialog("Enter the name of the Unit you want to delete: ");
                for (Unit u: units.getUnits()){
                    if (u.getUnitName().equals(unitNameDelete))
                        units.deleteUnit(u);
                }
                try {
                    FileOutputStream saveFile = new FileOutputStream("units.dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(units);
                    save.close();

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        contentPane.add(detailsPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

        try {
            FileOutputStream saveFile = new FileOutputStream("units.dat");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(units);
            save.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private void bOk_actionPerformed(ActionEvent e) {
//        StudentList studentList = new StudentList(Integer.parseInt(this.tfYear.getText()));
//        try{
//            FileOutputStream saveFile = new FileOutputStream("saveFile_" + this.jComboBox.getSelectedItem().toString() + ".dat");
//            ObjectOutputStream save = new ObjectOutputStream(saveFile);
//            save.writeObject(studentList);
//            save.close();
//        }
//        catch (FileNotFoundException ex){
//            ex.printStackTrace();
//        }
//        catch (IOException ex){
//            ex.printStackTrace();
//        }
        //StudentList list = new StudentList(Integer.parseInt(this.tfYear.getText()));
        for (Unit u: units.getUnits()){
            if (u.getUnitName().equals(jComboBox.getSelectedItem().toString()))
                selectedUnit = u;
        }
    	JFrame studentFrame = new StudentGUI(Integer.parseInt(this.tfYear.getText()),selectedUnit);
        studentFrame.pack();
        studentFrame.setVisible(true);



        dispose();

    }


}
