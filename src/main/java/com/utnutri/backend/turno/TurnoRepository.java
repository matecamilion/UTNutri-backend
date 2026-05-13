package com.utnutri.backend.turno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

    // Todos los turnos de pacientes que pertenecen al nutricionista logueado
    @Query("SELECT t FROM Turno t WHERE t.paciente.nutricionista.id = :nutriId ORDER BY t.fechaHora DESC")
    List<Turno> findAllByNutricionistaId(@Param("nutriId") Long nutriId);

    // Próximos turnos para el dashboard
    @Query("SELECT t FROM Turno t WHERE t.paciente.nutricionista.id = :nutriId " +
            "AND t.fechaHora >= :ahora AND t.estado = com.utnutri.backend.turno.EstadoTurno.PENDIENTE " +
            "ORDER BY t.fechaHora ASC")
    List<Turno> findProximosByNutricionistaId(@Param("nutriId") Long nutriId,
                                              @Param("ahora") LocalDateTime ahora);

    List<Turno> findByPacienteId(Long pacienteId);
}