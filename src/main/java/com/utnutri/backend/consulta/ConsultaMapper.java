package com.utnutri.backend.consulta;

import com.utnutri.backend.consulta.dto.ConsultaDTO;

public class ConsultaMapper {

    private ConsultaMapper() {}

    public static ConsultaDTO toDTO(Consulta c) {
        return ConsultaDTO.builder()
                .id(c.getId())
                .pacienteId(c.getPaciente().getId())
                .fecha(c.getFecha())
                .peso(c.getPeso())
                .altura(c.getAltura())
                .grasa(c.getGrasa())
                .masa(c.getMasa())
                .observaciones(c.getObservaciones())
                .build();
    }
}