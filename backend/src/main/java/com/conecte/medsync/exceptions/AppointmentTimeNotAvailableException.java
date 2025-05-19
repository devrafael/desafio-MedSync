package com.conecte.medsync.exceptions;

public class AppointmentTimeNotAvailableException extends RuntimeException {
    public AppointmentTimeNotAvailableException() {}
    public AppointmentTimeNotAvailableException(String message) {
        super(message);
    }
}
