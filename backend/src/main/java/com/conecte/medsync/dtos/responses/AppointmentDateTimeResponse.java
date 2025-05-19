package com.conecte.medsync.dtos.responses;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentDateTimeResponse(
        UUID appointmentDateTimeId,
        LocalDate date,
        LocalTime time,
        UserResponse doctor,
        Boolean aviability
) {
}
