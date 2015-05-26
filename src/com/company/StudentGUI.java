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

/**
 * Created by Dimos on 5/20/15.
 */
public class StudentGUI extends JFrame{


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

    private JTextField tName;
    private JTextField tTel;
    private JTextField tEmail;

    private JButton bCancel;
    private JButton bAdd;
    private JButton bFinish;

    StudentList studentList;

    public StudentGUI(String year, String unitName){

        contentPane = (JPanel)this.getContentPane();
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
        bCancel = new JButton("Cancel");
        buttonPane.add(bCancel);
        bAdd = new JButton("Add Student");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bAdd_actionPerformed(e);
            }
        });
        buttonPane.add(bAdd);
        bFinish = new JButton("Finish");
        bFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bFinish_actionPerformed(e);
            }
        });
        buttonPane.add(bFinish);

        infoPane = new JPanel();
        infoPane.setLayout(new FlowLayout());
        lYear = new JLabel(year);
        infoPane.add(lYear);
        lUnit = new JLabel(unitName);
        infoPane.add(lUnit);

        contentPane.add(infoPane, BorderLayout.NORTH);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(detailsPane, BorderLayout.CENTER);
    }

    private void bFinish_actionPerformed(ActionEvent e) {
        try{
            FileOutputStream saveFile = new FileOutputStream("saveFile_" + this.lUnit.getText()+ ".dat");
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


    public void bAdd_actionPerformed(ActionEvent e) {
        //studentNewList = new StudentList(2015);
        Student studentNew = new Student(this.tName.getText(), Integer.parseInt(this.tTel.getText()), this.tEmail.getText());

        try {
            FileInputStream saveFile = new FileInputStream("saveFile_" + this.lUnit.getText()+ ".dat");
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

        studentList.addStudent(studentNew);

        this.tName.setText("");
        this.tEmail.setText("");
        this.tTel.setText("");
        this.setVisible(true);
    }
}
