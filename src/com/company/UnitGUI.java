package com.company;

import javax.sql.rowset.serial.SerialArray;
import java.sql.Time;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.JarFile;

public class UnitGUI extends JFrame{

    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;
    private JPanel studentGUIPane;

    private JLabel lUnit;
    private JComboBox jComboBox;
    //private JLabel lYear;
    private JTextField tfYear;
    
    private JButton bConfirm;
    private JButton bBack;
    private JButton bAddUnit;
    private JButton bDeleteUnit;

    private UnitList units;
    private Unit selectedUnit;

    public UnitGUI() {

        //units = new UnitList();

//        try {
//            FileOutputStream saveFile = new FileOutputStream("units.dat");
//            ObjectOutputStream save = new ObjectOutputStream(saveFile);
//            save.writeObject(units);
//            save.close();
//
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

    	try {
            FileInputStream saveFile = new FileInputStream("units.dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.units = (UnitList) save.readObject();
            save.close();

        } catch (FileNotFoundException ex) {
            int res = JOptionPane.showConfirmDialog(null, "Do you want to create it?", "File Missing", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                units = new UnitList();
                try {
                    FileOutputStream saveFile = new FileOutputStream("units.dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(units);
                    save.close();

                } catch (FileNotFoundException ex2) {
                    ex.printStackTrace();
                } catch (IOException ex2) {
                    ex.printStackTrace();
                }

                dispose();
                JFrame back = new MainGUI();
                back.pack();
                back.setVisible(true);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        contentPane = (JPanel)this.getContentPane();
        this.setTitle("Choose Unit to create a Student class");

        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(1,2));
        //lYear = new JLabel("Year:");
        //detailsPane.add(lYear);
        //tfYear = new JTextField();
        //detailsPane.add(tfYear);
        lUnit = new JLabel("Unit:");
        detailsPane.add(lUnit);
        jComboBox = new JComboBox();
        for (Unit uni: units.getUnits()){
            jComboBox.addItem(uni.getUnitName());
        }
        detailsPane.add(jComboBox);
        jComboBox.setSelectedIndex(-1);
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bConfirm = new JButton("Confirm");
        bConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//bConfirm_actionPerformed(e);
            	try {
            	    
            	     bConfirm_actionPerformed(e);
            	}
            	catch (NumberFormatException wi) {
            		JOptionPane.showMessageDialog(null, "You need to specify Year with numbers", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            	catch (NullPointerException wi){
            		JOptionPane.showMessageDialog(null, "You need to select the specific unit", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        buttonPane.add(bConfirm);
        
        bBack = new JButton("Back");
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	MainGUI mg = new MainGUI();
            	mg.setLocationRelativeTo(null);
            	mg.pack();
            	mg.setVisible(true);	
            }
        });
        buttonPane.add(bBack);
        
        bAddUnit = new JButton("Add New Unit");
        bAddUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame addFrame = new AddUnitGui();
                addFrame.pack();
                addFrame.setLocationRelativeTo(null);
                addFrame.setVisible(true);
<<<<<<< Updated upstream
=======
                

>>>>>>> Stashed changes
            }
        });
        buttonPane.add(bAddUnit);
        
        bDeleteUnit = new JButton("Delete Unit");
        bDeleteUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String unitNameDelete = JOptionPane.showInputDialog("Enter the name of the Unit you want to delete: ");
                for (Unit u: units.getUnits()){
                    if (u.getUnitName().equals(unitNameDelete))
                        units.deleteUnit(u);
                }
                try {
                    FileOutputStream saveFile = new FileOutputStream("units.dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(units);
                    save.close();

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(detailsPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

        try {
            FileOutputStream saveFile = new FileOutputStream("units.dat");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(units);
            save.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private void bConfirm_actionPerformed(ActionEvent e) {
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
    	for (Unit u: units.getUnits()){
    		if (u.getUnitName().equals(jComboBox.getSelectedItem().toString()))
    			selectedUnit = u;
    	}
            
        
    	JFrame studentFrame = new StudentGUI(selectedUnit);
        studentFrame.pack();
        studentFrame.setVisible(true);
        studentFrame.setLocationRelativeTo(null);
        dispose();
        //studentFrame.setLocationRelativeTo(null);
        
    }
}
