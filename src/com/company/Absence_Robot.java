/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import javax.swing.JFrame;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Dimos
 */
public class Absence_Robot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //JFrame f = new StudentGUI();
        //f.pack();
        //f.setVisible(true);
        
       
        
        Date d = new Date(2015, 6, 24);
        Time t = new Time(12,0,0);
        Student s = new Student("Dimos", 123456, "sdfsdfsdf");
        Student s2 = new Student("Makis", 123456, "sdfsdfsdf");
        Student s3 = new Student("Marko", 123456, "sdfsdfsdf");
        Student s4 = new Student("Kostas", 123456, "sdfsdfsdf");
        Unit u = new Unit("OOP", "Eleutherakis", 4);
        StudentList list = new StudentList(2008);
        System.out.println(s.checkStudentAbsence(u));
        Absence a = new Absence(s,d, t, u);
        s.addAbsence(a);
        System.out.println(s.checkStudentAbsence(u));
        list.addStudent(s);
        list.addStudent(s2);
        list.addStudent(s3);
        list.addStudent(s4);
        
        System.out.println(list.getNameList());
        
        JFrame f2 = new TakeAbsenceGUI(list.getNameList());
        f2.pack();
        f2.setVisible(true);
    }
    
}
