package com.utnutri.backend.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanNutricionalRepository extends JpaRepository<PlanNutricional, Long> {
    Optional<PlanNutricional> findByPacienteId(Long pacienteId);
}