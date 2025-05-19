package com.conecte.medsync.services;



import com.conecte.medsync.dtos.responses.UserResponse;
import com.conecte.medsync.mappers.UserMapper;
import com.conecte.medsync.models.user.UserModel;
import com.conecte.medsync.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponse> getAllDoctors() {
        List<UserModel> doctors = userRepository.findAll();
        return doctors.stream()
                .map(userMapper::convertToResponse)
                .collect(Collectors.toList());
    }

    public UserModel getUserByUserId(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User not found"));
    }

}
