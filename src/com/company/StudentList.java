package com.company;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dimos on 5/11/15.
 */
public class StudentList implements Serializable {

    private ArrayList<Student> studentList;
    private int year;
    private int numberOfStudents;
    private Unit unit;

    public StudentList(int year, Unit unit){
        studentList = new ArrayList<Student>();
        this.year = year;
        this.unit = unit;
        this.numberOfStudents = 0;
    }

    public void addStudent(Student s){
        if (studentList.contains(s))
            System.out.println("Already in the list");
        else {
            studentList.add(s);
            numberOfStudents++;
        }
    }

    public void deleteStudent(Student s){
        if (studentList.contains(s)) {
            studentList.remove(s);
            numberOfStudents--;
        }
    }

    public Unit getUnit() {
        return unit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public ArrayList<String[]> getNameList(){
        String[] pair = new String[2];
        ArrayList<String[]> names = new ArrayList<String[]>();

        for (Student student : studentList){
            pair[0] = String.valueOf(student.getId());
            pair[1] = student.getName();

            names.add(pair);
            
        }
        return names;
    }

    public Student getStudentById(int id) throws NoStudentException{
        for (Student s: studentList){
            if (s.getId() == id){
                return s;
            }
        }
        throw new NoStudentException("No student found with this ID");
    }

    public ArrayList<Student> getArrayOfStudents(){
        return studentList;
    }
}
