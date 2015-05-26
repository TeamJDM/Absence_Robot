package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * Created by Dimos on 5/20/15.
 */
public class StudentGUI extends JFrame{

    private StudentList studentNewList;


    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;

    private JLabel headLabel;
    private JLabel lName;
    private JLabel lTel;
    private JLabel lEmail;

    private JTextField tName;
    private JTextField tTel;
    private JTextField tEmail;

    private JButton bCancel;
    private JButton bAdd;
    private JButton bFinish;

    public StudentGUI(){

        studentNewList = new StudentList(2015);

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
        buttonPane.add(bFinish);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(detailsPane, BorderLayout.CENTER);
    }


    public void bAdd_actionPerformed(ActionEvent e){
        //studentNewList = new StudentList(2015);
        Student studentNew = new Student(this.tName.getText(), Integer.parseInt(this.tTel.getText()), this.tEmail.getText());
        studentNewList.addStudent(studentNew);

        this.tName.setText("");
        this.tEmail.setText("");
        this.tTel.setText("");
        this.setVisible(true);
    }
}
