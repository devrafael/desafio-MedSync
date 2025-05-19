package com.conecte.medsync.controllers;

import com.conecte.medsync.dtos.requests.AppointmentDateTimeRequest;
import com.conecte.medsync.dtos.responses.AppointmentDateTimeResponse;
import com.conecte.medsync.services.AppointmentDateTimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
@PreAuthorize("hasRole('DOCTOR')")
@Tag(name = "AppointmentDateTimeController",
        description = "Controller para gerenciamento dos horários disponibilizados pelo médico")
public class AppointmentDateTimeController {

    private final AppointmentDateTimeService appointmentDateTimeService;

    public AppointmentDateTimeController(AppointmentDateTimeService appointmentDateTimeService) {
        this.appointmentDateTimeService = appointmentDateTimeService;
    }

    @PostMapping("/new")
    @Operation(summary = "Cria um novo horário", description = "Disponibiliza um novo horário para agendamento de consulta")
    @ApiResponse(responseCode = "201", description = "Horário criado com sucesso!")
    public ResponseEntity<Void> createAppointmentDateTime(
            @RequestBody AppointmentDateTimeRequest appointmentDateTimeRequest) {
        AppointmentDateTimeResponse appointmentDateTime = appointmentDateTimeService.createAppointmentSchedule(appointmentDateTimeRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(appointmentDateTime.appointmentDateTimeId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    @Operation(summary = "Retorna uma lista de horários", description = "Lista de horários disponibilizados por um médico")
    @ApiResponse(responseCode = "200", description = "Busca realizada!")
    public ResponseEntity<Set<AppointmentDateTimeResponse>> getAllAppointmentDateTime(
            @RequestParam String doctor) {
        Set<AppointmentDateTimeResponse> appointmentDateTimeResponses = appointmentDateTimeService.getAllAppointmentDateTime(doctor);
        return ResponseEntity.ok(appointmentDateTimeResponses);
    }


    @GetMapping("/{appointmentDateTimeId}")
    @Operation(summary = "Retorna um horário", description = "Horário disponibilizado por um médico")
    @ApiResponse(responseCode = "200", description = "Busca realizada!")
    public ResponseEntity<AppointmentDateTimeResponse> getAppointmentDateTimeById(
            @PathVariable UUID appointmentDateTimeId) {
        AppointmentDateTimeResponse appointmentDateTimeResponse = appointmentDateTimeService.getSchedule(appointmentDateTimeId);
        return ResponseEntity.ok(appointmentDateTimeResponse);
    }

    @PutMapping("/{appointmentDateTimeId}")
    @Operation(summary = "Atualiza um horário", description = "Atualiza um ou mais campos de um horário pré registrado")
    @ApiResponse(responseCode = "204", description = "Atualização realizada!")
    public ResponseEntity<Void> updateAppointment(
            @RequestBody AppointmentDateTimeRequest appointmentDateTimeRequest,
            @PathVariable UUID appointmentDateTimeId) {

        appointmentDateTimeService.updateAppointmentDateTime(appointmentDateTimeRequest, appointmentDateTimeId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{appointmentDateTimeId}")
    @Operation(summary = "Deletar um horário", description = "Realiza a remoção de um horário")
    @ApiResponse(responseCode = "204", description = "Remoção realizada!")
    public ResponseEntity<Void> deleteAppointmentDateTime(
            @PathVariable UUID appointmentDateTimeId) {
        appointmentDateTimeService.deleteAppointmentDateTime(appointmentDateTimeId);

        return ResponseEntity.noContent().build();
    }


}
