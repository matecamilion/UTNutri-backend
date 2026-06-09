package com.utnutri.backend.paciente.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PacienteCreateRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150)
    private String nombre;

    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "^(Masculino|Femenino|Otro)$", message = "El género debe ser Masculino, Femenino u Otro")
    private String genero;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 150)
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{7,30}$", message = "El teléfono debe contener entre 7 y 30 dígitos numéricos")
    private String telefono;
}