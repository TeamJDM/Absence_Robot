package com.company;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 */
public class UnitList implements Serializable{

    private ArrayList<Unit> units;

    /**
     * Constructs a UnitList object which is composed by some default units objects
     */
    public UnitList(){

        units = new ArrayList<Unit>();
        this.addUnit(new Unit("Math", "Veloudis", 12));
        this.addUnit(new Unit("Information Systems", "Diamantidis", 9));
        this.addUnit(new Unit("OOP", "Eleftherakis", 8));
        this.addUnit(new Unit("SAD", "Stamatopoulou", 6));
    }

    /**
     * Adds an unit object to the unitlist object
     * @param u unit object
     */
    public void addUnit(Unit u){
        units.add(u);
    }

    /**
     * Deletes an unit object from the unitlist object
     * @param u
     */
    public void deleteUnit(Unit u){
        units.remove(u);
    }

    /**
     * Gets all the unit objects which there are in the unitlist object as an ArrayList
     * @return an ArrayList of unit objects
     */
    public ArrayList<Unit> getUnits() {
        return units;
    }
}
