package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
	
    protected JPanel contentPane;
    private JPanel mainGuiPane;
    private JPanel buttonPane;
    
    private JButton bTakeAbsence;
    private JButton bStudentList;
    private JButton bViewList;
    private JButton bStudentRecord;
    private JButton bExit;
    
    public MainGUI(){
    	
    	contentPane = (JPanel)this.getContentPane();
    	contentPane.setPreferredSize(new Dimension(350,350));
        this.setTitle("MainWindow");
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(5,1));
        
        bStudentList = new JButton("Create New Student Class");
        bStudentList.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bStudentList);
        bStudentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		dispose();
            		bStudentList_actionPerformed(e);
            }
        });
        bViewList = new JButton("Look at Student Class for Specific Units");
        bViewList.setBackground(Color.white);
        buttonPane.add(bViewList);
        bViewList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{	
            		bViewList_actionPerformed(e);
            		dispose();
            	}
            	catch(Exception ex){
            		JOptionPane.showMessageDialog(null, "First you have to create new student class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        bTakeAbsence = new JButton("Take Absence");
        bTakeAbsence.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bTakeAbsence);
        bTakeAbsence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
            		bTakeAbsence_actionPerformed(e);
            		dispose();
            	}
            	catch(Exception ex){
            		JOptionPane.showMessageDialog(null, "First you have to create new student class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        bStudentRecord = new JButton("Search For Specific Student");
        bStudentRecord.setBackground(Color.white);
        buttonPane.add(bStudentRecord);
        bStudentRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{	
            		bStudentRecord_actionPerformed(e);
            		dispose();
            	}
            	catch(Exception ex){
            		JOptionPane.showMessageDialog(null, "First you have to create new student class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        bExit = new JButton("Exit");
        bExit.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bExit);
        mainGuiPane = new JPanel();
        mainGuiPane.add(buttonPane, BorderLayout.CENTER);
        contentPane.add(mainGuiPane);
        contentPane.add(buttonPane, BorderLayout.CENTER);
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
    }

    private void bTakeAbsence_actionPerformed(ActionEvent e) {
      	JFrame takeAbsenceFrame = new TakeAbsenceGUI();
        takeAbsenceFrame.pack();
        takeAbsenceFrame.setLocationRelativeTo(null);
        takeAbsenceFrame.setVisible(true);
    }

    public void bStudentList_actionPerformed(ActionEvent e){
        JFrame unitGui = new UnitGUI();
        unitGui.pack();
        unitGui.setLocationRelativeTo(null);
        unitGui.setVisible(true);
    }
    
    public void bViewList_actionPerformed(ActionEvent e){
        JFrame viewListGUI  = new ViewListGUI();
        viewListGUI.pack();
        viewListGUI.setLocationRelativeTo(null);
        viewListGUI.setVisible(true);
    }
    
    public void bStudentRecord_actionPerformed(ActionEvent e){
        JFrame unitGui  = new StudentRecordGUI();
        unitGui.pack();
        unitGui.setLocationRelativeTo(null);
        unitGui.setVisible(true);
    }
}
