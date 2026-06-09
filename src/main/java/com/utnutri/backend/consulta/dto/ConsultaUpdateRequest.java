package com.utnutri.backend.consulta.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ConsultaUpdateRequest {

    @PastOrPresent(message = "La fecha de la consulta no puede ser futura")
    private LocalDate fecha;

    @DecimalMin(value = "1.0", message = "El peso debe ser mayor a 0")
    @DecimalMax(value = "999.99", message = "El peso ingresado no es válido")
    private BigDecimal peso;

    @DecimalMin(value = "50.0", message = "La altura mínima es 50 cm")
    @DecimalMax(value = "220.0", message = "La altura máxima es 220 cm")
    private BigDecimal altura;

    @DecimalMin(value = "0.0", message = "El porcentaje de grasa no puede ser negativo")
    @DecimalMax(value = "100.0", message = "El porcentaje de grasa no puede superar 100")
    private BigDecimal grasa;

    @DecimalMin(value = "0.0", message = "El porcentaje de masa no puede ser negativo")
    @DecimalMax(value = "100.0", message = "El porcentaje de masa no puede superar 100")
    private BigDecimal masa;

    private String observaciones;
}