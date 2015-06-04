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

public class StudentGUI extends JFrame{

	private JFrame thisFrame;
    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;
    private JPanel infoPane;

    private JLabel headLabel;
    private JLabel lName;
    private JLabel lTel;
    private JLabel lEmail;
    private JLabel lYear;
    private JLabel lUnit;
    private JLabel lTime;


    private JTextField tName;
    private JTextField tTel;
    private JTextField tEmail;

    private JButton bAdd;
    private JButton bFinish;
    //private JButton jLoad;
    private JButton bDone;

    private StudentList studentList;

    public StudentGUI( Unit unit){

    	thisFrame = this;
        contentPane = (JPanel)this.getContentPane();

        studentList = new StudentList( unit);

        studentList = new StudentList( unit);
//        contentPane = (JPanel)this.getContentPane();
//        this.setTitle("Add Student");
        //contentPane.setPreferredSize(new Dimension(450,200));
        this.setSize(500,this.getHeight());

        this.setTitle("Add Student");

        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(3,3));
        lName = new JLabel("Student Name");
        detailsPane.add(lName);
        tName = new JTextField();
        detailsPane.add(tName);
        lTel = new JLabel("Student Telephone");
        detailsPane.add(lTel);
        tTel = new JTextField();
        detailsPane.add(tTel);
        lEmail = new JLabel("Student Email");
        detailsPane.add(lEmail);
        tEmail = new JTextField();
        detailsPane.add(tEmail);

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        
        bAdd = new JButton("Add Student");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                	tName.getText();
                	tTel.getText();
                	tEmail.getText();
                	bAdd_actionPerformed(e);
                }
                catch (NumberFormatException wi) {
            		JOptionPane.showMessageDialog(null, "You need to specify tel with numbers", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
                catch(Exception ex){
                	JOptionPane.showMessageDialog(null, "You need to specify the name, tel and email of new student", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPane.add(bAdd);
        
        bFinish = new JButton("Save List & Finish");
        bFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bFinish_actionPerformed(e);
            }
        });
        buttonPane.add(bFinish);


        infoPane = new JPanel();
        infoPane.setLayout(new FlowLayout());
        
        lUnit = new JLabel(unit.getUnitName());
        infoPane.add(lUnit);
       // infoPane.add(lTime);

        contentPane.add(infoPane, BorderLayout.NORTH);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(detailsPane, BorderLayout.CENTER);
    }

    private void bFinish_actionPerformed(ActionEvent e) {

        try{
            FileOutputStream saveFile = new FileOutputStream("saveFile_" + this.lUnit.getText()+ ".dat");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(this.studentList);
            save.close();
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        MainGUI mg = new MainGUI();
        //mg.setLocation();
        mg.pack();
        mg.setVisible(true);
        dispose();
    }

    public void bAdd_actionPerformed(ActionEvent e) {

        this.studentList.addStudent(new Student(this.tName.getText(), Integer.parseInt(this.tTel.getText()), this.tEmail.getText()));

        this.tName.setText("");
        this.tTel.setText("");
        this.tEmail.setText("");
    }
}
