package com.conecte.medsync.mappers.updates;

import com.conecte.medsync.dtos.requests.AppointmentDateTimeRequest;
import com.conecte.medsync.models.AppointmentDateTimeModel;
import com.conecte.medsync.models.user.UserModel;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AppointmentDateTimeUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "aviability", ignore = true)
    void updateAppointmentDateTime(AppointmentDateTimeRequest appointmentDateTimeRequest,
                                   @MappingTarget AppointmentDateTimeModel appointmentDateTimeModel);

    default UserModel map(String userId) {
        if (userId == null) return null;
        UserModel user = new UserModel();
        user.setUserId(userId);
        return user;
    }

}
