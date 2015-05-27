package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
	
	private JPanel contentPane;
    private JPanel buttonPane;
    
    private JButton bTakeAbsence;
    private JButton bStudentList;
    private JButton bViewList;
    private JButton bStudentRecord;
    private JButton bExit;
    
    public MainGUI(){
    	contentPane = (JPanel)this.getContentPane();
        this.setTitle("MainWindow");
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bTakeAbsence = new JButton("Take Absence");
        bTakeAbsence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bTakeAbsence_actionPerformed(e);
            }
        });
        buttonPane.add(bTakeAbsence);
        bStudentList = new JButton("Student List");
        buttonPane.add(bStudentList);
        bStudentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bStudentList_actionPerformed(e);
            }
        });
        bViewList = new JButton("View List");
        buttonPane.add(bViewList);
        bStudentRecord = new JButton("Student Record");
        buttonPane.add(bStudentRecord);
        bExit = new JButton("Exit");
        buttonPane.add(bExit);
        contentPane.add(buttonPane, BorderLayout.CENTER);
    }

    private void bTakeAbsence_actionPerformed(ActionEvent e) {
        JFrame takeAbsenceFrame = new TakeAbsenceGUI();
        takeAbsenceFrame.pack();
        takeAbsenceFrame.setVisible(true);
    }

    public void bStudentList_actionPerformed(ActionEvent e){
        JFrame unitGui  = new UnitGUI();
        unitGui.pack();
        unitGui.setVisible(true);

    }
}

