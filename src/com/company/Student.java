package com.company;

import com.sun.org.apache.bcel.internal.generic.I2F;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Student implements Serializable {

    private int id;
    private String name;
    private int tel;
    private String email;
    private ArrayList<Absence> absences;

    public Student(String name, int tel, String email){


        this.name = name;
        this.tel = tel;
        this.email = email;
        this.id = setId();
        absences = new ArrayList<Absence>();

    }

    public void setName(String name) throws WrongInputException{
        if (name instanceof String){
            this.name = name;
        }
        else
            throw new WrongInputException("Please Enter Your name again!");
    }

    public String getName(){
        return name;
    }

    public int getId() {
        return id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public int checkStudentAbsence(Unit u){
//        int unitAbsTotal = 0;
//        for (Absence a: absences) {
//            if (a.getUnit().getUnitName().equals(u.getUnitName())) {
//                unitAbsTotal++;
//            }
//        }
//        return unitAbsTotal;
//    }

    public void addAbsence(Absence absence){
        absences.add(absence);
    }

    public void deleteAbsence(Absence absence){
        absences.remove(absence);
    }

    protected int setId(){
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(20000);
    }

    public int checkAbsenceLimit( String unit){
        int absenceNumber = 0;
        for (Absence ab: absences){
            if (ab.getUnit().getUnitName().equals(unit)){
                absenceNumber++;
            }
        }
        return absenceNumber;
    }
}
