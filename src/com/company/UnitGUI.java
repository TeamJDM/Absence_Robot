package com.company;

import javax.swing.*;
import java.awt.*;
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
        detailsPane.add(jComboBox);
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bOk = new JButton("OK");
        buttonPane.add(bOk);

        contentPane.add(detailsPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);


    }


}
