package com.utnutri.backend.turno.dto;

import com.utnutri.backend.turno.EstadoTurno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDTO {

    private Long id;
    private Long pacienteId;
    private String nombrePaciente;
    private LocalDateTime fechaHora;
    private String observaciones;
    private EstadoTurno estado;
    private LocalDateTime createdAt;
}