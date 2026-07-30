package com.utnutri.backend.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNutricionistaId(Long nutricionistaId);
    Optional<Paciente> findByIdAndNutricionistaId(Long id, Long nutricionistaId);
    boolean existsByCorreoAndNutricionistaIdAndIdNot(String correo, Long nutricionistaId, Long id);
    boolean existsByTelefonoAndNutricionistaIdAndIdNot(String telefono, Long nutricionistaId, Long id);
    boolean existsByCorreoAndNutricionistaId(String correo, Long nutricionistaId);
    boolean existsByTelefonoAndNutricionistaId(String telefono, Long nutricionistaId);
}