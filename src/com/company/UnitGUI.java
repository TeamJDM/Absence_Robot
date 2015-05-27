package com.company;

import javax.sql.rowset.serial.SerialArray;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.JarFile;

/**
 * Created by Dimos on 5/26/15.
 */
public class UnitGUI extends JFrame{

    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;

    private JComboBox jComboBox;
    private JLabel lYear;
    private JTextField tfYear;
    private JButton bOk;

    public UnitGUI() {

        contentPane = (JPanel)this.getContentPane();
        this.setTitle("Choose Unit to create a Student List");

        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(2,2));
        lYear = new JLabel("Year:");
        detailsPane.add(lYear);
        tfYear = new JTextField();
        detailsPane.add(tfYear);
        jComboBox = new JComboBox();
        jComboBox.addItem("OOP");
        jComboBox.addItem("SAD");
        detailsPane.add(jComboBox);
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bOk = new JButton("OK");
        bOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bOk_actionPerformed(e);
            }
        });
        buttonPane.add(bOk);

        contentPane.add(detailsPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);


    }

    private void bOk_actionPerformed(ActionEvent e) {
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
        JFrame studentFrame = new StudentGUI(Integer.parseInt(this.tfYear.getText()), this.jComboBox.getSelectedItem().toString());
        studentFrame.pack();
        studentFrame.setVisible(true);
    }


}
