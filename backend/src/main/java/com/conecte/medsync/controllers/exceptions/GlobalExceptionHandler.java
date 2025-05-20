package com.conecte.medsync.controllers.exceptions;

import com.conecte.medsync.exceptions.*;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;



@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<ProblemDetail> handleAppointmentException(AppointmentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Appointment Error");
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(DateTimeRegistredException.class)
    public ResponseEntity<ProblemDetail> handleDateTimeRegistredException(DateTimeRegistredException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("DateTime Registration Error");
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(UserRegistredException.class)
    public ResponseEntity<ProblemDetail> handleUserRegistredException(UserRegistredException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("User Registration Error");
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }


}
