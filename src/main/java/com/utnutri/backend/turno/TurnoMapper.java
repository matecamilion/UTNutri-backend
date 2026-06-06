package com.utnutri.backend.turno;

import com.utnutri.backend.turno.dto.TurnoDTO;

public class TurnoMapper {

    private TurnoMapper() {}

    public static TurnoDTO toDTO(Turno t) {
        return new TurnoDTO(
                t.getId(),
                t.getPaciente().getId(),
                t.getPaciente().getNombre(),
                t.getFechaHora(),
                t.getObservaciones(),
                t.getEstado(),
                t.getCreatedAt()
        );
    }
}
