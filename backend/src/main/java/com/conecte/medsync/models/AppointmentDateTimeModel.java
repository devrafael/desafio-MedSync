package com.conecte.medsync.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class AppointmentDateTimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID appointmentDateTimeId;
    private LocalDate date;
    private LocalTime time;
    private String doctor; //apenas representacao (temporario)
    private Boolean aviability;

    public AppointmentDateTimeModel(LocalDate date, LocalTime time, String doctor) {
        this.date = date;
        this.time = time;
        this.doctor = doctor;
    }

    public AppointmentDateTimeModel() {}

    public UUID getAppointmentDateTimeId() {
        return appointmentDateTimeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDoctor() {
        return doctor;
    }

    public Boolean getAviability() {
        return aviability;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setAviability(Boolean aviability) {
        this.aviability = aviability;
    }
}
