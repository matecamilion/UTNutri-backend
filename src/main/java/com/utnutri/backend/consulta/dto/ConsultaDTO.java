package com.utnutri.backend.consulta.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class ConsultaDTO {
    private Long id;
    private Long pacienteId;
    private LocalDate fecha;
    private BigDecimal peso;
    private BigDecimal altura;
    private BigDecimal grasa;
    private BigDecimal masa;
    private String observaciones;

}
