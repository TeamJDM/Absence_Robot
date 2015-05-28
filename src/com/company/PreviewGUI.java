package com.company;


import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Dimos on 5/27/15.
 */
public class PreviewGUI extends JFrame{

    private JLabel lUnit;
    private JPanel contentPane;
    private JPanel infoPanel;
    private JButton bOk;

    private StudentList studentList;

    public PreviewGUI(String unit) {

        try {
            FileInputStream saveFile = new FileInputStream("saveFile_" + unit+ ".dat");
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

        contentPane = (JPanel) this.getContentPane();
        this.setTitle("Preview");

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(studentList.getArrayOfStudents().size(), 3));
        for (Student s: studentList.getArrayOfStudents()){
            infoPanel.add(new JLabel(String.valueOf(s.getId())));
            infoPanel.add(new JLabel(s.getName()));
            infoPanel.add(new JLabel(String.valueOf(s.checkAbsenceLimit(unit))));
        }

        contentPane.add(infoPanel, BorderLayout.CENTER);
    }
}
