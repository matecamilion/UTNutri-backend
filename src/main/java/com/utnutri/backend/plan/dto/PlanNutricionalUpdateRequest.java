package com.utnutri.backend.plan.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanNutricionalUpdateRequest {
    private String desayuno;
    private String almuerzo;
    private String merienda;
    private String cena;
    private String snacks;
    private String notas;
}