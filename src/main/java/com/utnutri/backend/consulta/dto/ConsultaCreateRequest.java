package com.utnutri.backend.consulta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ConsultaCreateRequest {

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    private BigDecimal peso;
    private BigDecimal altura;
    private BigDecimal grasa;
    private BigDecimal masa;
    private String observaciones;
}