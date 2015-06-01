package com.company;

import javax.sql.rowset.serial.SerialArray;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

    private ArrayList<Unit> units;
    private Unit selectedUnit;

    public UnitGUI() {

        //units = new Unit[2];
        //Unit oop = new Unit("OOP", "Eleutherakis", 8);
        //Unit sad = new Unit("SAD", "Stamatopoulou", 6);
        units = new ArrayList<Unit>();
        units.add(new Unit("OOP", "Eleutherakis", 8));
        units.add(new Unit("SAD", "Stamatopoulou", 6));

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
        for (Unit uni: units){
            jComboBox.addItem(uni.getUnitName());
        }
//        Unit oop = new Unit("OOP", "Eleutherakis", 8);
//        jComboBox.addItem(oop.getUnitName());
//        Unit sad = new Unit("SAD", "Stamatopoulou", 6);
//        jComboBox.addItem(sad.getUnitName());
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

        contentPane.add(detailsPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

        //contentPaneRoot.add(contentPane);


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
        for (Unit u: units){
            if (u.getUnitName().equals(jComboBox.getSelectedItem().toString()))
                selectedUnit = u;
        }
    	JFrame studentFrame = new StudentGUI(Integer.parseInt(this.tfYear.getText()),selectedUnit);
        studentFrame.pack();
        studentFrame.setVisible(true);



        dispose();

    }


}
