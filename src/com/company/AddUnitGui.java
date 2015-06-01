package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Dimos on 6/1/15.
 */
public class AddUnitGui extends JFrame{

    private JLabel lUnitName;
    private JLabel lProfName;
    private JLabel lAbsences;
    private JTextField tUnitName;
    private JTextField tProfName;
    private JTextField tAbsences;
    private JButton bAdd;
    private JButton bFinish;

    private JPanel contentPane;
    private JPanel infoPane;
    private JPanel buttonPane;

    private UnitList units;

    public AddUnitGui(){

        contentPane = (JPanel) this.getContentPane();
        this.setTitle("Add Unit Details");

        lUnitName = new JLabel("Unit Name:");
        lProfName = new JLabel("Professor Name:");
        lAbsences = new JLabel("Absences Permitted:");
        tUnitName = new JTextField();
        tProfName = new JTextField();
        tAbsences = new JTextField();

        infoPane = new JPanel();
        infoPane.setLayout(new GridLayout(3,3));
        infoPane.add(lUnitName);
        infoPane.add(tUnitName);
        infoPane.add(lProfName);
        infoPane.add(tProfName);
        infoPane.add(lAbsences);
        infoPane.add(tAbsences);

        bAdd = new JButton("Add Unit");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                units.addUnit(new Unit(tUnitName.getText(), tProfName.getText(),Integer.parseInt(tAbsences.getText())));
                tUnitName.setText("");
                tProfName.setText("");
                tAbsences.setText("");

                try {
                    FileOutputStream saveFile = new FileOutputStream("units.dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(units);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        buttonPane.add(bAdd);
        bFinish = new JButton("Finish");
        bFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame unitFrame = new UnitGUI();
                unitFrame.pack();
                unitFrame.setVisible(true);
            }
        });
        buttonPane.add(bFinish);
        contentPane.add(infoPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

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
    }

}
