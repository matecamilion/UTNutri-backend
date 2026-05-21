package com.utnutri.backend.consulta.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ConsultaUpdateRequest {
    private LocalDate fecha;
    private BigDecimal peso;
    private BigDecimal altura;
    private BigDecimal grasa;
    private BigDecimal masa;
    private String observaciones;
}