package com.utnutri.backend.paciente;

import com.utnutri.backend.paciente.dto.PacienteDTO;

public class PacienteMapper { //pasa el entity a un dto

    private PacienteMapper() {}

    public static PacienteDTO toDTO(Paciente p) {
        return PacienteDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .genero(p.getGenero())
                .fechaNacimiento(p.getFechaNacimiento())
                .correo(p.getCorreo())
                .telefono(p.getTelefono())
                .build();
    }
}