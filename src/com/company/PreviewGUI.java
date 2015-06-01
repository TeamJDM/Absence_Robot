package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PreviewGUI extends JFrame{

    private JLabel lUnit;
    private JPanel contentPane;
    private JPanel infoPanel;
    private JPanel buttonPanel;
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
        contentPane.setPreferredSize(new Dimension(200,200));
        this.setTitle("Preview");

        buttonPanel = new JPanel();
        bOk = new JButton("Ok");
        bOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	TakeAbsenceGUI ta = new TakeAbsenceGUI();
            	ta.pack();
            	ta.setVisible(true);
            	dispose();
            }
        });
        buttonPanel.add(bOk);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(studentList.getArrayOfStudents().size(), 3));
        for (Student s: studentList.getArrayOfStudents()){
            infoPanel.add(new JLabel(String.valueOf(s.getId())));
            infoPanel.add(new JLabel(s.getName()));
            infoPanel.add(new JLabel(String.valueOf(s.checkAbsenceLimit(unit))));
        }

        contentPane.add(infoPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
}
