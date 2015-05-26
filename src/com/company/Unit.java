package com.company;

/**
 * Created by Dimos on 5/11/15.
 */
public class Unit {

    private String profName;
    private String unitName;
    private int absencesPermitted;
    private StudentList list;

    public Unit(String n, String profName, int aPermit){
        unitName = n;
        this.profName = profName;
        absencesPermitted = aPermit;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getAbsencesPermitted() {
        return absencesPermitted;
    }

    public void assignStudentList(StudentList listInput){
        this.list = listInput;
    }
}
