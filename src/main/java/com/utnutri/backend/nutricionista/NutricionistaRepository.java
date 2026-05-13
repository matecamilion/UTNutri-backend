package com.utnutri.backend.nutricionista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
    Optional<Nutricionista> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}