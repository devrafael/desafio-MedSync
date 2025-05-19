package com.conecte.medsync.mappers;

import com.conecte.medsync.dtos.responses.AppointmentResponse;
import com.conecte.medsync.models.AppointmentModel;
import com.conecte.medsync.models.user.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = AppointmentDateTimeMapper.class)
public interface AppointmentMapper {

    @Mapping(target = "appointmentId", source = "appointmentId")
    @Mapping(target = "requestAt", source = "requestAt")
    @Mapping(target = "doctor", expression = "java(extractDoctorId(appointmentModel))")
    @Mapping(target = "patient", expression = "java(appointmentModel.getPatient() != null ? appointmentModel.getPatient().getFullName() : null)")
    AppointmentResponse convertToResponse(AppointmentModel appointmentModel);

    default String map(UserModel doctor) {
        if (doctor == null) return null;
        return doctor.getFullName();
    }

    default String extractDoctorId(AppointmentModel model) {
        if (model == null || model.getAppointmentDateTime() == null || model.getAppointmentDateTime().getDoctor() == null) {
            return null;
        }
        return model.getAppointmentDateTime().getDoctor().getUserId();
    }

}
