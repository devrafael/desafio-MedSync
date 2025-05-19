package com.conecte.medsync.mappers;

import com.conecte.medsync.dtos.responses.UserResponse;
import com.conecte.medsync.models.user.UserModel;
import org.mapstruct.Mapper;

import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {


        UserResponse convertToResponse(UserModel userModel);

}
