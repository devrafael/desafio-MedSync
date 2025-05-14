package com.conecte.medsync.mappers;

import com.conecte.medsync.dtos.requests.AppointmentDateTimeRequest;
import com.conecte.medsync.dtos.responses.AppointmentDateTimeResponse;
import com.conecte.medsync.models.AppointmentDateTimeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AppointmentDateTimeMapper {

    @Mapping(target = "appointmentDateTimeId", ignore = true)
    @Mapping(target = "aviability", ignore = true)
    AppointmentDateTimeModel convertToModel(AppointmentDateTimeRequest appointmentDateTimeRequest);

    AppointmentDateTimeResponse convertToResponse(AppointmentDateTimeModel appointmentDateTimeModel);
}