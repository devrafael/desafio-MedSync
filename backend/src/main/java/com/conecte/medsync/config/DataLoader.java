package com.conecte.medsync.config;

import com.conecte.medsync.models.user.UserModel;
import com.conecte.medsync.models.user.UserRole;
import com.conecte.medsync.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner runner(){
        return args -> {

            UserModel userMedico = userRepository.findUserByEmail("medico@email.com");
            UserModel userPaciente = userRepository.findUserByEmail("paciente@email.com");

            if ((userMedico == null) && (userPaciente == null)) {
                userMedico = new UserModel();
                userMedico.setEmail("medico@email.com");
                userMedico.setPassword(passwordEncoder.encode("medico"));
                userMedico.setFullName("Medico Exemplo");
                userMedico.setRole(UserRole.DOCTOR);
                userRepository.save(userMedico);

                userPaciente = new UserModel();
                userPaciente.setEmail("paciente@email.com");
                userPaciente.setPassword(passwordEncoder.encode("paciente"));
                userPaciente.setFullName("Paciente Exemplo");
                userPaciente.setRole(UserRole.PATIENT);
                userRepository.save(userPaciente);

            }


        };
    }

}
