package com.sportsmanagement.teamservice.sportsteamsubdomain.utils.exceptions;

public class DuplicateIdentifierException extends RuntimeException{

    public DuplicateIdentifierException() {}

    public DuplicateIdentifierException(String message) { super(message); }

    public DuplicateIdentifierException(Throwable cause) { super(cause); }

    public DuplicateIdentifierException(String message, Throwable cause) { super(message, cause); }
}
