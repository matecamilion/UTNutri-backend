package com.utnutri.backend.paciente.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PacienteDTO { //esto devuelve la API
    private Long id;
    private String nombre;
    private String genero;
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;
}
