package com.utnutri.backend.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPacienteId(Long pacienteId);
    Optional<Consulta> findByIdAndPacienteId(Long id, Long pacienteId);
    List<Consulta> findByPacienteIdOrderByFechaDesc(Long pacienteId);
}