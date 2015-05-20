package com.company;

/**
 * Created by Dimos on 5/20/15.
 */
public class WrongInputException extends Exception {

    public WrongInputException() {
        super();
    }

    public WrongInputException(String message) {
        super(message);
    }
}
