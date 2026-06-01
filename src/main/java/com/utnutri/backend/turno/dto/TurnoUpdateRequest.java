package com.utnutri.backend.turno.dto;

import com.utnutri.backend.turno.EstadoTurno;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TurnoUpdateRequest { // lo que es para actualizar, por ahora solo la fecha u observaciones

    @Future(message = "La fecha debe ser futura")
    private LocalDateTime fechaHora;

    private String observaciones;

    private EstadoTurno estado;
}