package com.company;

import java.util.ArrayList;

/**
 * Created by Dimos on 5/11/15.
 */
public class StudentList {

    private ArrayList<Student> studentList;
    private int year;
    private int numberOfStudents;

    public StudentList(int year){
        studentList = new ArrayList<Student>();
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public ArrayList<String> getNameList(){
        ArrayList<String> names = new ArrayList();
        for (Student student : studentList){
            names.add(student.getName());
            
        }
        return names;
    }
}
