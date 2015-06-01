package com.company;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Dimos on 5/13/15.
 */
public class Absence implements Serializable{

    private String date;
    //private Time time;
    private Unit unit;

    public Absence(String date, Unit unit){

        this.date = date;
        //this.time = time;
        this.unit = unit;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public Time getTime() {
//        return time;
//    }

//    public void setTime(Time time) {
//        this.time = time;
//    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }


}
