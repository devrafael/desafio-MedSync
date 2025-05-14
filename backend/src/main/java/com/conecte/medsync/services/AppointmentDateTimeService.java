package com.conecte.medsync.services;

import com.conecte.medsync.dtos.requests.AppointmentDateTimeRequest;
import com.conecte.medsync.dtos.responses.AppointmentDateTimeResponse;
import com.conecte.medsync.exceptions.DateTimeRegistredException;
import com.conecte.medsync.mappers.AppointmentDateTimeMapper;
import com.conecte.medsync.mappers.updates.AppointmentDateTimeUpdateMapper;
import com.conecte.medsync.models.AppointmentDateTimeModel;
import com.conecte.medsync.repositories.AppointmentDateTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppointmentDateTimeService {

    private final AppointmentDateTimeRepository appointmentDateTimeRepository;
    private final AppointmentDateTimeMapper appointmentDateTimeMapper;
    private final AppointmentDateTimeUpdateMapper appointmentDateTimeUpdateMapper;

    public AppointmentDateTimeService(AppointmentDateTimeRepository appointmentDateTimeRepository,
                                      AppointmentDateTimeMapper appointmentDateTimeMapper,
                                      AppointmentDateTimeUpdateMapper appointmentDateTimeUpdateMapper) {
        this.appointmentDateTimeRepository = appointmentDateTimeRepository;
        this.appointmentDateTimeMapper = appointmentDateTimeMapper;
        this.appointmentDateTimeUpdateMapper = appointmentDateTimeUpdateMapper;
    }

    //Pendencias: Associar usuario autenticado (doutor) com os horários
    public AppointmentDateTimeResponse createAppointmentSchedule(AppointmentDateTimeRequest appointmentScheduleRequest) {
        AppointmentDateTimeModel newAppointmentDateTime = appointmentDateTimeMapper.convertToModel(appointmentScheduleRequest);
        newAppointmentDateTime.setAviability(true);

        checkIfAppointmentDateTimeExists(newAppointmentDateTime);

        appointmentDateTimeRepository.save(newAppointmentDateTime);

        return appointmentDateTimeMapper.convertToResponse(newAppointmentDateTime);

    }

    public void checkIfAppointmentDateTimeExists(AppointmentDateTimeModel appointmentDateTime){
        Optional<AppointmentDateTimeModel> appointmentDateTimeModelRegistred = appointmentDateTimeRepository.findByDateTimeAndDoctor(
                appointmentDateTime.getDate(),
                appointmentDateTime.getTime(),
                appointmentDateTime.getDoctor()
        );

        if(appointmentDateTimeModelRegistred.isPresent()) {
            throw new DateTimeRegistredException("The time slot is already registered!");
        }
    }

    public Set<AppointmentDateTimeResponse> getAllAppointmentDateTime(String doctor){
        List<AppointmentDateTimeModel> appointmentsDateTime = appointmentDateTimeRepository.findDateTimeByDoctor(doctor);
        return appointmentsDateTime.stream()
                .map(appointmentDateTimeMapper::convertToResponse)
                .collect(Collectors.toSet());
    }

    public void updateAppointmentDateTime(AppointmentDateTimeRequest appointmentDateTimeRequest, UUID appointmentAvailabilityId) {
        AppointmentDateTimeModel appointmentRegistred = appointmentDateTimeRepository
                .findById(appointmentAvailabilityId)
                .orElseThrow(() -> new DateTimeRegistredException("Schedule with the specified ID was not found. ID: " + appointmentAvailabilityId));

        appointmentDateTimeUpdateMapper.updateAppointmentDateTime(appointmentDateTimeRequest, appointmentRegistred);
        appointmentDateTimeRepository.save(appointmentRegistred);
    }

    public AppointmentDateTimeModel findAppointmentDateTimeByDateTimeAndDoctor(LocalDate date, LocalTime time, String doctor){
        Optional<AppointmentDateTimeModel> dateTimeRegistred = appointmentDateTimeRepository.findByDateTimeAndDoctor(date, time, doctor);
        return dateTimeRegistred.orElseThrow(()-> new DateTimeRegistredException("Date, time or doctor not registred!"));
    }

    public void deleteAppointmentDateTime(UUID appointmentAvailabilityId){
        appointmentDateTimeRepository.deleteById(appointmentAvailabilityId);
    }

    public void isScheduleUnavailable(AppointmentDateTimeModel appointmentDateTimeModel){
          appointmentDateTimeModel.setAviability(false);

    }




}
