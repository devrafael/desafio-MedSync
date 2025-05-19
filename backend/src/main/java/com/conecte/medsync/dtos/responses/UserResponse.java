package com.conecte.medsync.dtos.responses;

import com.conecte.medsync.models.user.UserRole;

public record UserResponse(
        String userId,
        String fullName,
        String email,
        UserRole role
) {
}
