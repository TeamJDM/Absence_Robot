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

    /**
     * Constracts a Student object with a name telephone email and an empty list of absences objects
     * @param name name of the student
     * @param tel the telephone of the student
     * @param email the email of the student
     */
    public Student(String name, int tel, String email){


        this.name = name;
        this.tel = tel;
        this.email = email;
        this.id = setId();
        absences = new ArrayList<Absence>();

    }

    /**
     *
     * @param name of the student
     * @throws WrongInputException
     */
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


    /**
     * Takes an absence object and assigns it to the student object
     * @param absence absence object
     * @see Absence
     */
    public void addAbsence(Absence absence){
        absences.add(absence);
    }

    /**
     * Deletes an absence object from the student object
     * @param absence Absence object
     * @see Absence
     */
    public void deleteAbsence(Absence absence){
        absences.remove(absence);
    }

    /**
     * Sets randomly the id attribute with a number from 10000 to 20000
     * @return an integer from 10000 to 20000
     * @see Random
     */
    protected int setId(){
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(20000);
    }

    /**
     * Iterates through the absences' list of the student object and for every absence object increase the counter by 1
     * @param unit a unit object
     * @return the number of absences
     */
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
