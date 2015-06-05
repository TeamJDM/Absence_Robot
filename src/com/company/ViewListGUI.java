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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ViewListGUI extends JFrame {


    private StudentList studentList;
    private UnitList unitList;


    private ArrayList<JCheckBox> checkBoxes;

    private JPanel contentPane;
    private JPanel tablePane;
    private JPanel infoPanel;
    private JPanel buttonPane;

    private JLabel lUnit;
    private JComboBox unitsBox;
    private JButton bAdd;
    private JButton bDelete;
    private JButton bDone;

    //private Unit selectedUnit;

    public ViewListGUI() {

        checkBoxes = new ArrayList<JCheckBox>();

        try {
        	FileInputStream saveFile = new FileInputStream("units.dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.unitList = (UnitList) save.readObject();
            save.close();

        } catch (FileNotFoundException ex) {
    		JOptionPane.showMessageDialog(null, "First you have to create New Student Class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        contentPane = (JPanel) this.getContentPane();
        contentPane.setPreferredSize(new Dimension(400, 100));
        tablePane = new JPanel();
        this.setTitle("Look at the created Student Classes");

        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        lUnit = new JLabel("Unit:");
        infoPanel.add(lUnit);
        unitsBox = new JComboBox();
        try{
	        for (Unit u : unitList.getUnits()) {
	            unitsBox.addItem(u.getUnitName());
	        }
        }
        catch(NullPointerException ex){
    		//JOptionPane.showMessageDialog(null, "First you have to create New Student Class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
        	
        }
        //infoPanel.add(unitsBox);
        unitsBox.setSelectedIndex(-1);
        unitsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click_actionPerformed(e);
            }
        });

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bAdd = new JButton("Add Students");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel inputDialog = new JPanel();
                JPanel info = new JPanel();
                JPanel buttons = new JPanel();
                JLabel lName = new JLabel("Name");
                JTextField tName = new JTextField();
                JLabel lTel = new JLabel("Telephone Number");
                JTextField tTel = new JTextField();
                JLabel lEmail = new JLabel("Email");
                JTextField tEmail = new JTextField();
                info.setLayout(new GridLayout(3, 3));
                info.add(lName);
                info.add(tName);
                info.add(lTel);
                info.add(tTel);
                info.add(lEmail);
                info.add(tEmail);
                inputDialog.add(info, BorderLayout.CENTER);

                int result = JOptionPane.showConfirmDialog(null, inputDialog,
                        "Please Enter New Students Info", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try{
                    	studentList.addStudent(new Student(tName.getText(), Integer.parseInt(tTel.getText()), tEmail.getText()));
                    }
                    catch(Exception ex){
                    	JOptionPane.showMessageDialog(null,"Specify student name and email with letters and telephone with numbers."+ "\n" + "Try again by cliking add students button", "ATTENTION !!!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                try {
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                } catch (FileNotFoundException ex) {
                	
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                tablePane.removeAll();

                try {
                    FileInputStream saveFile = new FileInputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
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
                
                tablePane.setLayout(new GridLayout(studentList.getNameList().size(), 4));
                int i = 0;
                checkBoxes.clear();
                for (Student s : studentList.getArrayOfStudents()) {
                    checkBoxes.add(new JCheckBox(String.valueOf(s.getId()), false));
                    tablePane.add(checkBoxes.get(i));
                    tablePane.add(new JLabel(s.getName()));
                    tablePane.add(new JLabel(String.valueOf(s.checkAbsenceLimit(studentList.getUnit().getUnitName()))));
                    tablePane.add(new JLabel(String.valueOf(studentList.getUnit().getAbsencesPermitted())));

                    i++;
                }
                buttonPane.add(bAdd);
                buttonPane.add(bDelete);

                buttonPane.repaint();
                contentPane.add(tablePane, BorderLayout.CENTER);
                tablePane.revalidate();
                tablePane.repaint();


            }
        });


        bDelete = new JButton("Delete Students");
        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s;
                for (JCheckBox jch : checkBoxes) {
                    if (jch.isSelected()) {
                    	
                        s = studentList.getStudentById(Integer.parseInt(jch.getText()));
                        studentList.deleteStudent(s);

                    }
                    else{
                    	JOptionPane.showMessageDialog(null,"Please select the student that you want to delete", "ATTENTION !!!", JOptionPane.ERROR_MESSAGE);

                    }
                }

                try {
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                } catch (FileNotFoundException ex) {
                	ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                tablePane.removeAll();

                try {
                    FileInputStream saveFile = new FileInputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
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

                tablePane.setLayout(new GridLayout(studentList.getNameList().size()+1, 4));
                tablePane.add(new JLabel("ID"));
                tablePane.add(new JLabel("Name"));
                tablePane.add(new JLabel("Absences"));
                tablePane.add(new JLabel("Ansences Permitted"));
                int i = 0;
                checkBoxes.clear();
                for (Student si : studentList.getArrayOfStudents()) {
                    checkBoxes.add(new JCheckBox(String.valueOf(si.getId()), false));
                    tablePane.add(checkBoxes.get(i));
                    tablePane.add(new JLabel(si.getName()));
                    tablePane.add(new JLabel(String.valueOf(si.checkAbsenceLimit(studentList.getUnit().getUnitName()))));
                    tablePane.add(new JLabel(String.valueOf(studentList.getUnit().getAbsencesPermitted())));

                    i++;
                }
                buttonPane.add(bAdd);
                buttonPane.add(bDelete);

                buttonPane.repaint();
                contentPane.add(tablePane, BorderLayout.CENTER);
                tablePane.revalidate();
                tablePane.repaint();
            }
        });


        infoPanel.add(unitsBox);
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

    private void click_actionPerformed(ActionEvent e) {
    	this.setSize(600,400);
        tablePane.removeAll();

        try {
            FileInputStream saveFile = new FileInputStream("saveFile_" + this.unitsBox.getSelectedItem().toString() + ".dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.studentList = (StudentList) save.readObject();
            save.close();
            
            tablePane.setLayout(new GridLayout(studentList.getNameList().size()+1, 4));
            tablePane.add(new JLabel("ID"));
            tablePane.add(new JLabel("Name"));
            tablePane.add(new JLabel("Absences"));
            tablePane.add(new JLabel("Absences Permitted"));
            int i = 0;
            for (Student s : studentList.getArrayOfStudents()) {
                checkBoxes.add(new JCheckBox(String.valueOf(s.getId()), false));
                tablePane.add(checkBoxes.get(i));
                tablePane.add(new JLabel(s.getName()));
                tablePane.add(new JLabel(String.valueOf(s.checkAbsenceLimit(studentList.getUnit().getUnitName()))));
                tablePane.add(new JLabel(String.valueOf(studentList.getUnit().getAbsencesPermitted())));

                i++;
            }

        } catch (FileNotFoundException ex) {
        	JOptionPane.showMessageDialog(null, "The unit that you chose does not have student class."+"\n " + "Try another unit or add students here.", "ATTENTION !!!", JOptionPane.ERROR_MESSAGE);
        	
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        buttonPane.add(bAdd);
        buttonPane.add(bDelete);

        buttonPane.repaint();
        contentPane.add(tablePane, BorderLayout.CENTER);
        tablePane.revalidate();
        tablePane.repaint();
    }
    
}
