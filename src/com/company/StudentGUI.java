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

    private JButton bAdd;
    private JButton bFinish;
    private JButton jLoad;
    private JButton bBack;

    private StudentList studentList;

    public StudentGUI(int year, String unitName){

        contentPane = (JPanel)this.getContentPane();
<<<<<<< HEAD
        studentList = new StudentList(year);
//        contentPane = (JPanel)this.getContentPane();
//        this.setTitle("Add Student");
=======
        contentPane.setPreferredSize(new Dimension(450,200));
        this.setTitle("Add Student");
>>>>>>> origin/master

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
        jLoad = new JButton("Load");
        jLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bLoad_actionPerformed(e);
            }
        });
        buttonPane.add(jLoad);
        
        bBack = new JButton("Back");
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	UnitGUI mg = new UnitGUI();
            	mg.pack();
            	mg.setVisible(true);
            	
            }
        });
        buttonPane.add(bBack);

        infoPane = new JPanel();
        infoPane.setLayout(new FlowLayout());
        lYear = new JLabel(String.valueOf(year));
        infoPane.add(lYear);
        lUnit = new JLabel(unitName);
        infoPane.add(lUnit);

        contentPane.add(infoPane, BorderLayout.NORTH);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(detailsPane, BorderLayout.CENTER);


    }

    private void bLoad_actionPerformed(ActionEvent e) {

    }

    private void bFinish_actionPerformed(ActionEvent e) {

//        for (Student s: studentList.getArrayOfStudents()){
//            studentList.addStudent(s);
//        }

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
    }


    public void bAdd_actionPerformed(ActionEvent e) {

        this.studentList.addStudent(new Student(this.tName.getText(), Integer.parseInt(this.tTel.getText()), this.tEmail.getText()));

        this.tName.setText("");
        this.tTel.setText("");
        this.tEmail.setText("");


    }
}
