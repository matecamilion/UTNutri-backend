package com.utnutri.backend.paciente.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PacienteUpdateRequest {

    // min=1: rechaza string vacío "" pero null pasa (campo no enviado en partial update)
    @Size(min = 1, max = 150, message = "El nombre debe tener entre 1 y 150 caracteres")
    private String nombre;

    @Pattern(regexp = "^(Masculino|Femenino|Otro)$", message = "El género debe ser Masculino, Femenino u Otro")
    private String genero;

    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 150)
    private String correo;

    @Pattern(regexp = "^[0-9]{7,30}$", message = "El teléfono debe contener entre 7 y 30 dígitos numéricos")
    private String telefono;
}