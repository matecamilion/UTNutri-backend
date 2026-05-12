package com.utnutri.backend.consulta;

import com.utnutri.backend.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "consultas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(precision = 5, scale = 2)
    private BigDecimal altura;

    @Column(precision = 4, scale = 2)
    private BigDecimal grasa;

    @Column(precision = 5, scale = 2)
    private BigDecimal masa;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
