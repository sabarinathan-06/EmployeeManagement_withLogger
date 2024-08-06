package com.ideas2it.exception;

public class EmployeeException extends Exception{
    public EmployeeException(String message, Throwable error) {
        super(message, error);
    }
}