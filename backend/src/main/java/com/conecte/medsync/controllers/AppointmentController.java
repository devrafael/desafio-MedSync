package com.conecte.medsync.controllers;

import com.conecte.medsync.dtos.requests.AppointmentRequest;
import com.conecte.medsync.dtos.responses.AppointmentResponse;
import com.conecte.medsync.security.config.SecurityConfig;
import com.conecte.medsync.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
@Tag(name = "AppointmentController",
        description = "Controller para o agendamento de consultas médicas")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class AppointmentController {

    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('PATIENT')")
    @Operation(summary = "Cria uma consulta", description = "Paciente realiza o agendamento de uma consulta médica")
    @ApiResponse(responseCode = "201", description = "Consulta agendada com sucesso!")
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        AppointmentResponse appointmentResponse = appointmentService.createAppointment(appointmentRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(appointmentResponse.appointmentId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    @Operation(summary = "Lista de consultas agendadas", description = "Médico visualiza sua agenda de consultas")
    @ApiResponse(responseCode = "200", description = "Busca com sucesso!")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Set<AppointmentResponse>> getAllAppointments(
            @RequestParam String doctor
    ) {
        Set<AppointmentResponse> appointments = appointmentService.getAllAppointments(doctor);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{patient}")
    @PreAuthorize("hasRole('PATIENT')")
    @Operation(summary = "Retorno de consultas marcadas", description = "Lista de consultas marcadas de um paciente")
    @ApiResponse(responseCode = "200", description = "Busca realizada!")
    public ResponseEntity<Set<AppointmentResponse>> getAllPatientAppointments(
            @PathVariable String patient) {
        Set<AppointmentResponse> appointmentDateTimeResponses = appointmentService.getAllPatientAppointments(patient);
        return ResponseEntity.ok(appointmentDateTimeResponses);
    }

    @PatchMapping("/{appointmentId}")
    @PreAuthorize("hasRole('DOCTOR')")
    @Operation(summary = "Finalizar consulta", description = "Médico registra a finalização da consulta com o paciente")
    @ApiResponse(responseCode = "204", description = "Busca com sucesso!")
    public ResponseEntity<AppointmentResponse> getAllAppointmentsByStatus(
            @PathVariable UUID appointmentId){
        appointmentService.finishAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }





}
