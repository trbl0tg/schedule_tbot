package com.ntu;

public class NoSuchGooglePlayGameFoundException extends Exception {

    public NoSuchGooglePlayGameFoundException() {
        super();
    }

    public NoSuchGooglePlayGameFoundException(String message) {
        super(message);
    }

    public NoSuchGooglePlayGameFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchGooglePlayGameFoundException(Throwable cause) {
        super(cause);
    }

}
