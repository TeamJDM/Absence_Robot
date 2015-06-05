/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class TakeAbsenceGUI extends JFrame{

    private StudentList studentList;
    private ArrayList<Student> absentStudents;
    private UnitList units;
    
    private JPanel contentPane;
    private JPanel tablePane;
    private JPanel infoPanel;
    private JPanel buttonPane;

    private ArrayList<JCheckBox> checkBoxes;
    private JComboBox unitsBox;
    private JButton bLoad;
    private JButton bSubmit;
    private JButton bDone;
    private JLabel lUnit;
    private JLabel lProfessor;
    
    private JLabel lClassRoom;
    private JComboBox cClasses;
    private JLabel lDate;
    private JLabel tfDate;
    private JLabel lTime;
    private JLabel tfTime;
    private JLabel lShowUnit;
    private JLabel lShowProf;

    private Date date;

    public TakeAbsenceGUI(){

        checkBoxes = new ArrayList<JCheckBox>();
        date = new Date();
        absentStudents = new ArrayList<Student>();
        //studentList = new StudentList(date.getYear());
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


        contentPane = (JPanel) this.getContentPane();
        //contentPane.setPreferredSize(new Dimension(300,200));
        tablePane = new JPanel();

        
        this.setTitle("Take Absences");

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2,2));

        JLabel lUnitsChoose = new JLabel("Please choose a unit to Take Absences");
        infoPanel.add(lUnitsChoose);
        unitsBox = new JComboBox();
        cClasses = new JComboBox();
        for (Unit u: units.getUnits()){
            unitsBox.addItem(u.getUnitName());
        }
        infoPanel.add(unitsBox);
        unitsBox.setSelectedIndex(-1);

        cClasses.addItem("L1");
        cClasses.addItem("L3-L4");
        cClasses.addItem("L5");
        cClasses.addItem("L6");
        cClasses.addItem("Rodoula");
        cClasses.addItem("ETHRA");
        cClasses.addItem("TALIS");
        cClasses.addItem("MP Room");

        infoPanel.add(new JLabel("Choose a Classroom: "));
        infoPanel.add(cClasses);
        cClasses.setSelectedIndex(-1);
       
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bLoad = new JButton("Load");
        bLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(unitsBox.getSelectedItem() != null & cClasses.getSelectedItem() !=null) {
                	bLoad_actionPerformed(e);
                }
                else{
                	JOptionPane.showMessageDialog(null, "Please select the missing fields", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
               	}    
            }
        });
        buttonPane.add(bLoad);
        bSubmit = new JButton("Submit");
        bSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                	
                	bSubmit_actionPerformed(e);
                	dispose();
                }
                catch(NullPointerException ex){
                	JOptionPane.showMessageDialog(null, "Wrong button! Please decide on unit and classroom fields and then load the class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPane.add(bSubmit);
        
        bDone = new JButton("Done");
        bDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	MainGUI mg = new MainGUI();
            	mg.setLocationRelativeTo(null);
            	mg.pack();
            	mg.setVisible(true);
            	
            }
        });
        buttonPane.add(bDone);



        contentPane.add(buttonPane, BorderLayout.SOUTH);

        contentPane.add(infoPanel, BorderLayout.NORTH);

    }

    private void bSubmit_actionPerformed(ActionEvent e) {
    	
    	for (JCheckBox jChB: checkBoxes){
    		if (jChB.isSelected()){
	            absentStudents.add(studentList.getStudentById(Integer.parseInt(jChB.getText())));
	        }
    		else{
                JOptionPane.showMessageDialog(null,"Sorry ! You did not select any student to take absence for him/her. Try again", "ATTENTION !!!", JOptionPane.ERROR_MESSAGE);            

    		}
    	}
	    for (Student stud : studentList.getArrayOfStudents()) {
	    	if (absentStudents.contains(stud)) {
	    		if (stud.checkAbsenceLimit(this.unitsBox.getSelectedItem().toString()) < 7){
	    			stud.addAbsence(new Absence(date.toString(), studentList.getUnit(), cClasses.getSelectedItem().toString()));
	            }	
	        }
	    }
        try{
            FileOutputStream saveFile = new FileOutputStream("saveFile_" + this.unitsBox.getSelectedItem().toString()+ ".dat");
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

        JFrame f12 = new PreviewGUI(this.unitsBox.getSelectedItem().toString());
        f12.pack();
        f12.setLocationRelativeTo(null);
        f12.setVisible(true);

        absentStudents.clear();


    }

    private void bLoad_actionPerformed(ActionEvent e) {
        try {
            FileInputStream saveFile = new FileInputStream("saveFile_" + this.unitsBox.getSelectedItem().toString()+ ".dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.studentList = (StudentList) save.readObject();
            save.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "The unit that you chose does not have students in it. Please add students in this unit if you want to take absences.", "ATTENTION !!!", JOptionPane.ERROR_MESSAGE);            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        tablePane.setLayout(new GridLayout(studentList.getNameList().size(), 4));
        int i = 0;
        for (Student s: studentList.getArrayOfStudents()){
            checkBoxes.add(new JCheckBox(String.valueOf(s.getId()), false));
            tablePane.add(checkBoxes.get(i));
            tablePane.add(new JLabel(s.getName()));
            tablePane.add(new JLabel(String.valueOf(s.checkAbsenceLimit(studentList.getUnit().getUnitName()))));
            tablePane.add(new JLabel(String.valueOf(studentList.getUnit().getAbsencesPermitted())));

            i++;
        }

        infoPanel.removeAll();
        infoPanel.setLayout(new GridLayout(4,4));
        //lYear = new JLabel("Year:");
        //lShowYear = new JLabel(String.valueOf(studentList.getYear()));
        lUnit = new JLabel("Unit:");
        lShowUnit = new JLabel(unitsBox.getSelectedItem().toString());
        lProfessor = new JLabel("Professor:");
        lShowProf = new JLabel(studentList.getUnit().getProfName());
        infoPanel.add(lUnit);
        infoPanel.add(lShowUnit);

        infoPanel.add(lProfessor);
        infoPanel.add(lShowProf);
        infoPanel.add(new JLabel("Date & Time: "));
        infoPanel.add(new JLabel(String.valueOf(date)));
        infoPanel.add(new JLabel("Classroom: "));
        infoPanel.add(new JLabel(cClasses.getSelectedItem().toString()));

        contentPane.add(tablePane, BorderLayout.CENTER);
        contentPane.add(infoPanel, BorderLayout.NORTH);
        this.pack();
        bLoad.setVisible(false);
    }
}
