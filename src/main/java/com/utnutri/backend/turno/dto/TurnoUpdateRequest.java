package com.utnutri.backend.turno.dto;

import com.utnutri.backend.turno.EstadoTurno;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TurnoUpdateRequest {
    // @Future solo aplica cuando fechaHora no es null (partial update seguro)
    @Future(message = "La fecha y hora del turno deben ser futuras")
    private LocalDateTime fechaHora;
    private String observaciones;
    private EstadoTurno estado;
}