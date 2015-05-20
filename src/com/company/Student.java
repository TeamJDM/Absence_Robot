package com.company;

import java.util.ArrayList;

/**
 * Created by Dimos on 5/11/15.
 */
public class Student {

    private int id;
    private String name;
    private int tel;
    private String email;
    private ArrayList<Absence> absences;

    public Student(String name, int tel, String email){

        this.name = name;
        this.tel = tel;
        this.email = email;
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

    /*private int setId(){

    }*/

    public void addAbsence(Absence absence){
        absences.add(absence);
    }

    public void deleteAbsence(Absence absence){
        absences.remove(absence);
    }

}
