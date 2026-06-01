package com.utnutri.backend.turno;

import com.utnutri.backend.nutricionista.Nutricionista;
import com.utnutri.backend.turno.dto.TurnoCreateRequest;
import com.utnutri.backend.turno.dto.TurnoDTO;
import com.utnutri.backend.turno.dto.TurnoUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listar(
            @AuthenticationPrincipal Nutricionista nutricionista) {
        return ResponseEntity.ok(turnoService.listarTurnos(nutricionista));
    }

    @GetMapping("/proximos")
    public ResponseEntity<List<TurnoDTO>> proximos(
            @AuthenticationPrincipal Nutricionista nutricionista) {
        return ResponseEntity.ok(turnoService.listarProximos(nutricionista));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<TurnoDTO>> porPaciente(
            @PathVariable Long pacienteId,
            @AuthenticationPrincipal Nutricionista nutricionista) {
        return ResponseEntity.ok(turnoService.listarPorPaciente(pacienteId, nutricionista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> obtener(
            @PathVariable Long id,
            @AuthenticationPrincipal Nutricionista nutricionista) {
        return ResponseEntity.ok(turnoService.obtenerPorId(id, nutricionista));
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> crear(
            @Valid @RequestBody TurnoCreateRequest request,
            @AuthenticationPrincipal Nutricionista nutricionista) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(turnoService.crear(request, nutricionista));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TurnoDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TurnoUpdateRequest request,
            @AuthenticationPrincipal Nutricionista nutricionista) {
        return ResponseEntity.ok(turnoService.actualizar(id, request, nutricionista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id,
            @AuthenticationPrincipal Nutricionista nutricionista) {
        turnoService.eliminar(id, nutricionista);
        return ResponseEntity.noContent().build();
    }
}