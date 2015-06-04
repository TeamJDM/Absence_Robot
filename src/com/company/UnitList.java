package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class UnitList implements Serializable{

    private ArrayList<Unit> units;

    public UnitList(){

        units = new ArrayList<Unit>();
        this.addUnit(new Unit("Math", "Veloudis", 12));
        this.addUnit(new Unit("Information Systems", "Diamantidis", 9));
        this.addUnit(new Unit("OOP", "Eleftherakis", 8));
        this.addUnit(new Unit("SAD", "Stamatopoulou", 6));
    }

    public void addUnit(Unit u){
        units.add(u);
    }

    public void deleteUnit(Unit u){
        units.remove(u);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }
}
