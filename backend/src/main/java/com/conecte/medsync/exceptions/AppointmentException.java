package com.conecte.medsync.exceptions;

public class AppointmentException extends RuntimeException {

    public AppointmentException() {}

    public AppointmentException(String message) {
        super(message);
    }
}
