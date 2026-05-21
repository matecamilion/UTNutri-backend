package com.utnutri.backend.plan;

import com.utnutri.backend.plan.dto.PlanNutricionalDTO;

public class PlanNutricionalMapper {

    private PlanNutricionalMapper() {}

    public static PlanNutricionalDTO toDTO(PlanNutricional p) {
        return PlanNutricionalDTO.builder()
                .id(p.getId())
                .pacienteId(p.getPaciente().getId())
                .desayuno(p.getDesayuno())
                .almuerzo(p.getAlmuerzo())
                .merienda(p.getMerienda())
                .cena(p.getCena())
                .snacks(p.getSnacks())
                .notas(p.getNotas())
                .build();
    }
}