package com.conecte.medsync.mappers;


import com.conecte.medsync.dtos.requests.AuthenticationRequest;
import com.conecte.medsync.models.user.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {



//    AppointmentResponse convertToResponse(AppointmentModel appointmentModel);

}
