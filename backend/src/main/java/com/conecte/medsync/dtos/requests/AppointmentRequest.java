package com.conecte.medsync.dtos.requests;

public record AppointmentRequest(
        AppointmentDateTimeRequest appointmentDateTimeRequest,
        String patient
) {
}
