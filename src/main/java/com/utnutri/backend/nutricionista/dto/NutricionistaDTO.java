package com.utnutri.backend.nutricionista.dto;

import com.utnutri.backend.nutricionista.Nutricionista;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NutricionistaDTO {

    private Long id;
    private String username;
    private String email;
    private String nombre;
    private LocalDateTime createdAt;

    public static NutricionistaDTO from(Nutricionista n) {
        return NutricionistaDTO.builder()
                .id(n.getId())
                .username(n.getUsername())
                .email(n.getEmail())
                .nombre(n.getNombre())
                .createdAt(n.getCreatedAt())
                .build();
    }
}