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
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s'.-]+$", message = "El nombre solo puede contener letras, espacios, guiones y apóstrofes")
    private String nombre;

    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "^(Masculino|Femenino|Otro)$", message = "El género debe ser Masculino, Femenino u Otro")
    private String genero;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "El correo debe incluir un dominio válido (ej. .com)")
    @Size(max = 150)
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{7,30}$", message = "El teléfono debe contener entre 7 y 30 dígitos numéricos")
    private String telefono;

    @AssertTrue(message = "La fecha de nacimiento no es coherente")
    public boolean isFechaNacimientoCoherente() {
        return fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now().minusYears(120));
    }
}