package com.conecte.medsync.models;

import com.conecte.medsync.models.user.UserModel;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class AppointmentDateTimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentDateTimeId;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "userId")
    private UserModel doctor;
    private Boolean aviability;

    public AppointmentDateTimeModel(LocalDate date, LocalTime time, UserModel doctor) {
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

    public UserModel getDoctor() {
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

    public void setDoctor(UserModel doctor) {
        this.doctor = doctor;
    }

    public void setAviability(Boolean aviability) {
        this.aviability = aviability;
    }
}
