package com.utnutri.backend.turno.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TurnoCreateRequest {

    @NotNull(message = "El paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La fecha debe ser futura")
    private LocalDateTime fechaHora;

    private String observaciones;
}