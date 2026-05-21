package com.utnutri.backend.plan.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanNutricionalDTO {
    private Long id;
    private Long pacienteId;
    private String desayuno;
    private String almuerzo;
    private String merienda;
    private String cena;
    private String snacks;
    private String notas;
}