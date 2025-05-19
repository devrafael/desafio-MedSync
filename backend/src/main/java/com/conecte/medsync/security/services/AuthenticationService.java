package com.conecte.medsync.security.services;

import com.conecte.medsync.dtos.requests.AuthenticationRequest;
import com.conecte.medsync.dtos.requests.RegisterRequest;
import com.conecte.medsync.dtos.responses.LoginResponse;
import com.conecte.medsync.exceptions.UserRegistredException;
import com.conecte.medsync.models.user.UserModel;
import com.conecte.medsync.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationService(UserRepository userRepository,
                                 AuthenticationManager authenticationManager,
                                 TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public LoginResponse Login(AuthenticationRequest authenticationRequest){
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationRequest.email(), authenticationRequest.password());

        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return new LoginResponse(token);
    }

    public UserModel Register(RegisterRequest registerRequest) {
        if(this.userRepository.findByEmail(registerRequest.email()) != null){
            throw new UserRegistredException("User registred!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());

        UserModel newUser = new UserModel(
                registerRequest.email(),
                encryptedPassword,
                registerRequest.fullName(),
                registerRequest.role());

        this.userRepository.save(newUser);
        return newUser;
    }
}
