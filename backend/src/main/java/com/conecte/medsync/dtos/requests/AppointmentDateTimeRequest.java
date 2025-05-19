package com.conecte.medsync.dtos.requests;

public record AppointmentDateTimeRequest(
        String date,
        String time,
        String doctor
) {
}
