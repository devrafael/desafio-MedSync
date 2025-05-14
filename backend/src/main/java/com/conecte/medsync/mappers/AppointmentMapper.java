package com.conecte.medsync.mappers;

import com.conecte.medsync.dtos.requests.AppointmentRequest;
import com.conecte.medsync.dtos.responses.AppointmentResponse;
import com.conecte.medsync.models.AppointmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = AppointmentDateTimeMapper.class)
public interface AppointmentMapper {

    @Mapping(target = "appointmentId", ignore = true)
    @Mapping(target = "requestAt", ignore = true)
    @Mapping(source = "appointmentDateTimeRequest", target = "appointmentDateTime")
    @Mapping(target = "appointmentCompleted", ignore = true)
    AppointmentModel convertToModel(AppointmentRequest appointmentRequest);

    @Mapping(target = "appointmentId", source = "appointmentId")
    @Mapping(target = "requestAt", source = "requestAt")
    @Mapping(target = "doctor", source = "appointmentDateTime.doctor")
    @Mapping(target = "patient", source = "patient")
    AppointmentResponse convertToResponse(AppointmentModel appointmentModel);

}
