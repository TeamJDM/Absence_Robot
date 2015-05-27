package com.company;

/**
 * Created by Dimos on 5/27/15.
 */
public class NoStudentException extends Exception{

    public NoStudentException() {
        super();
    }

    public NoStudentException(String message) {
        super(message);
    }
}
