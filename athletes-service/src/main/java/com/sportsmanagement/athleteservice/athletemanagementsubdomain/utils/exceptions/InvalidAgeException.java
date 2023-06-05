package com.sportsmanagement.athleteservice.athletemanagementsubdomain.utils.exceptions;

public class InvalidAgeException extends RuntimeException{

    public InvalidAgeException() {}

    public InvalidAgeException(String message) { super(message); }

    public InvalidAgeException(Throwable cause) { super(cause); }

    public InvalidAgeException(String message, Throwable cause) { super(message, cause); }
}
