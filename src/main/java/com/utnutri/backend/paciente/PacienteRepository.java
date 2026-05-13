package com.utnutri.backend.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNutricionistaId(Long nutricionistaId);
    Optional<Paciente> findByIdAndNutricionistaId(Long id, Long nutricionistaId);
}