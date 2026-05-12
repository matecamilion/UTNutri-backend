package com.utnutri.backend.plan;

import com.utnutri.backend.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "planes_nutricionales")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PlanNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false, unique = true)
    private Paciente paciente;

    @Column(columnDefinition = "TEXT")
    private String desayuno;

    @Column(columnDefinition = "TEXT")
    private String almuerzo;

    @Column(columnDefinition = "TEXT")
    private String merienda;

    @Column(columnDefinition = "TEXT")
    private String cena;

    @Column(columnDefinition = "TEXT")
    private String snacks;

    @Column(columnDefinition = "TEXT")
    private String notas;
}
