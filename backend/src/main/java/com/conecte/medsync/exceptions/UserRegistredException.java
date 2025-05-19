package com.conecte.medsync.exceptions;

public class UserRegistredException extends RuntimeException {

    public UserRegistredException() {}

    public UserRegistredException(String message) {
        super(message);
    }
}
