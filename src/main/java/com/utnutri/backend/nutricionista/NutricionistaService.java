package com.utnutri.backend.nutricionista;

import com.utnutri.backend.nutricionista.dto.NutricionistaDTO;
import com.utnutri.backend.nutricionista.dto.NutricionistaUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NutricionistaService {

    private final NutricionistaRepository nutricionistaRepository;
    private final PasswordEncoder passwordEncoder;

    // ─── Obtener todos ───────────────────────────────────────────────────────
    public List<NutricionistaDTO> getAll() {
        return nutricionistaRepository.findAll()
                .stream()
                .map(NutricionistaDTO::from)
                .toList();
    }

    // ─── Obtener por ID ──────────────────────────────────────────────────────
    public NutricionistaDTO getById(Long id) {
        Nutricionista nutricionista = findOrThrow(id);
        return NutricionistaDTO.from(nutricionista);
    }

    // ─── Actualizar ──────────────────────────────────────────────────────────
    public NutricionistaDTO update(Long id, NutricionistaUpdateRequest request) {
        Nutricionista existente = findOrThrow(id);

        // Validar unicidad solo si el valor cambió
        if (!existente.getUsername().equals(request.getUsername())
                && nutricionistaRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un nutricionista con ese username");
        }
        if (!existente.getEmail().equals(request.getEmail())
                && nutricionistaRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un nutricionista con ese email");
        }

        existente.setUsername(request.getUsername());
        existente.setEmail(request.getEmail());
        existente.setNombre(request.getNombre());

        // La password es opcional en el update
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return NutricionistaDTO.from(nutricionistaRepository.save(existente));
    }

    // ─── Eliminar ────────────────────────────────────────────────────────────
    public void delete(Long id) {
        findOrThrow(id);
        nutricionistaRepository.deleteById(id);
    }

    // ─── Helper privado ──────────────────────────────────────────────────────
    private Nutricionista findOrThrow(Long id) {
        return nutricionistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Nutricionista no encontrado con id: " + id));
    }
}