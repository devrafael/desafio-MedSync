package com.conecte.medsync.services;

import com.conecte.medsync.dtos.requests.AppointmentRequest;
import com.conecte.medsync.dtos.responses.AppointmentDateTimeResponse;
import com.conecte.medsync.dtos.responses.AppointmentResponse;
import com.conecte.medsync.exceptions.AppointmentException;
import com.conecte.medsync.mappers.AppointmentMapper;
import com.conecte.medsync.models.AppointmentDateTimeModel;
import com.conecte.medsync.models.AppointmentModel;
import com.conecte.medsync.models.user.UserModel;
import com.conecte.medsync.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentDateTimeService appointmentDateTimeService;
    private final AppointmentMapper appointmentMapper;
    private final UserService userService;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              AppointmentDateTimeService appointmentDateTimeService,
                              AppointmentMapper appointmentMapper,
                              UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentDateTimeService = appointmentDateTimeService;
        this.appointmentMapper = appointmentMapper;
        this.userService = userService;
    }

    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {

        AppointmentDateTimeModel appointmentDateTimeModel = appointmentDateTimeService.findAppointmentDateTimeByDateTimeAndDoctor(
                LocalDate.parse(appointmentRequest.appointmentDateTimeRequest().date()),
                LocalTime.parse(appointmentRequest.appointmentDateTimeRequest().time()),
                appointmentRequest.appointmentDateTimeRequest().doctor()
                );
        UserModel patientRegistred = userService.getUserByUserId(appointmentRequest.patient());
        AppointmentModel newAppointment = new AppointmentModel(appointmentDateTimeModel, patientRegistred);
        newAppointment.setAppointmentCompleted(false);

        appointmentDateTimeService.isScheduleUnavailable(appointmentDateTimeModel);


        appointmentRepository.save(newAppointment);
        return appointmentMapper.convertToResponse(newAppointment);
    }

    public Set<AppointmentResponse> getAllAppointments(String doctor) {
        List<AppointmentModel> appointments = appointmentRepository.findByAppointmentsByDoctor(doctor);
        return appointments.stream()
                .map(appointmentMapper::convertToResponse)
                .collect(Collectors.toSet());
    }

    public Set<AppointmentResponse> getAllPatientAppointments(String patient){
        List<AppointmentModel> appointments = appointmentRepository.findByPatientUserId(patient);
        return appointments.stream()
                .map(appointmentMapper::convertToResponse)
                .collect(Collectors.toSet());
    }


    public List<AppointmentResponse> getAllAppointmentsByStatus(Boolean status, String patient){
        List<AppointmentModel> appointmentResponseListByStatus = appointmentRepository
                .findByCompletedAndPatient(status, patient);
        return appointmentResponseListByStatus.stream()
                .map(appointmentMapper::convertToResponse)
                .collect(Collectors.toList());
    }

    public void finishAppointment(UUID appointmentId) {
        AppointmentModel appointmentRegistred = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException("Appointment not found"));
        appointmentRegistred.setAppointmentCompleted(true);
        appointmentRepository.save(appointmentRegistred);
    }


}
