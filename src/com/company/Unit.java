package com.company;

import java.io.Serializable;

/**
 * Created by Dimos on 5/11/15.
 */
public class Unit implements Serializable{

    private String profName;
    private String unitName;
    private int absencesPermitted;
    private StudentList list;

    /**
     * Constructs a Unit object with a name, professor name and the the number of the absences that are permitted
     * @param n the unit name
     * @param profName the professor name
     * @param aPermit the number of absences are permitted
     */
    public Unit(String n, String profName, int aPermit){
        unitName = n;
        this.profName = profName;
        absencesPermitted = aPermit;
    }

    /**
     *
     * @return the professor name
     */
    public String getProfName() {
        return profName;
    }

    /**
     *
     * @param profName the professor name
     */
    public void setProfName(String profName) {
        this.profName = profName;
    }

    /**
     *
     * @return the unit name
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     *
     * @param unitName the unit name
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     *
     * @return the absences permitted
     */
    public int getAbsencesPermitted() {
        return absencesPermitted;
    }


}
