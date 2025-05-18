package com.conecte.medsync.controllers;

import com.conecte.medsync.dtos.requests.AppointmentRequest;
import com.conecte.medsync.dtos.responses.AppointmentResponse;
import com.conecte.medsync.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
@Tag(name = "AppointmentController",
        description = "Controller para o agendamento de consultas médicas")
public class AppointmentController {

    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/new")
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

    //Pendencia: Adicionar parametro para receber o identificador do médico e filtrar a lista
    @GetMapping()
    @Operation(summary = "Lista de consultas agendadas", description = "Médico visualiza sua agenda de consultas")
    @ApiResponse(responseCode = "200", description = "Busca com sucesso!")
    public ResponseEntity<Set<AppointmentResponse>> getAllAppointments(
            @RequestParam String doctor
    ) {
        Set<AppointmentResponse> appointments = appointmentService.getAllAppointments(doctor);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/patient")
    @Operation(summary = "Lista de consultas do paciente", description = "Paciente visualiza suas consultas (futuras e passadas)")
    @ApiResponse(responseCode = "200", description = "Busca com sucesso!")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointmentsByStatus(
            @RequestParam
            Boolean status,
            @RequestParam
            String patient){
        List<AppointmentResponse> listAppointmentsByStatus = appointmentService.getAllAppointmentsByStatus(status, patient);
        return ResponseEntity.ok(listAppointmentsByStatus);
    }

    @PatchMapping("/{appointmentId}")
    @Operation(summary = "Finalizar consulta", description = "Médico registra a finalização da consulta com o paciente")
    @ApiResponse(responseCode = "204", description = "Busca com sucesso!")
    public ResponseEntity<AppointmentResponse> getAllAppointmentsByStatus(
            @PathVariable UUID appointmentId){
        appointmentService.finishAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }





}
