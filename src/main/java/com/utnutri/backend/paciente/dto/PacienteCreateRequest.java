package com.utnutri.backend.paciente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PacienteCreateRequest { //lo que mandamos a crear

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150)
    private String nombre;

    @Size(max = 20)
    private String genero;

    private LocalDate fechaNacimiento;

    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 150)
    private String correo;

    @Size(max = 30)
    private String telefono;
}