package com.utnutri.backend.turno.dto;

import com.utnutri.backend.turno.EstadoTurno;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TurnoUpdateRequest {
    //sacamos @future, lo manejamos en el front
    private LocalDateTime fechaHora;
    private String observaciones;
    private EstadoTurno estado;
}