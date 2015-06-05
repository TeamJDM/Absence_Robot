package com.company;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Absence implements Serializable{

    private String date;
    private Unit unit;
    private String clroom;

    /**
     *
     * @param date of the Absence as String
     * @param unit the unit object in which the absence will be taken
     * @param classroom the classroom as string
     */
    public Absence(String date, Unit unit, String classroom){

        this.date = date;
        this.unit = unit;
        this.clroom = classroom;
    }

    /**
     * @return the date that the Absence Object was created as String
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date the date of the Absence as String
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return the unit object that the Absence has been taken to
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     *
     * @param unit the unit in which to take the Absence
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
