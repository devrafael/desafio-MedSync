package com.conecte.medsync.repositories;

import com.conecte.medsync.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<UserModel, String> {
    UserDetails findByEmail(String email);

    @Query("SELECT u FROM UserModel u WHERE u.email = :email")
    UserModel findUserByEmail(@Param("email")String email);

}
