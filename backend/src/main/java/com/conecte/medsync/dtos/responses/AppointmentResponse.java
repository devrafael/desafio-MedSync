package com.conecte.medsync.dtos.responses;


import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID appointmentId,
        AppointmentDateTimeResponse appointmentDateTime,
        LocalDateTime requestAt,
        String patient,
        Boolean appointmentCompleted,
        String doctor
) {
}
