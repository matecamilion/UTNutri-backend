package com.utnutri.backend.paciente;

import com.utnutri.backend.nutricionista.Nutricionista;
import com.utnutri.backend.paciente.dto.PacienteCreateRequest;
import com.utnutri.backend.paciente.dto.PacienteDTO;
import com.utnutri.backend.paciente.dto.PacienteUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public List<PacienteDTO> getAll(@AuthenticationPrincipal Nutricionista nutri) {
        return pacienteService.getAll(nutri.getId());
    }

    @GetMapping("/{id}")
    public PacienteDTO getById(@PathVariable Long id,
                               @AuthenticationPrincipal Nutricionista nutri) {
        return pacienteService.getById(id, nutri.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDTO create(@Valid @RequestBody PacienteCreateRequest request,
                              @AuthenticationPrincipal Nutricionista nutri) {
        return pacienteService.create(request, nutri);
    }

    @PutMapping("/{id}")
    public PacienteDTO update(@PathVariable Long id,
                              @Valid @RequestBody PacienteUpdateRequest request,
                              @AuthenticationPrincipal Nutricionista nutri) {
        return pacienteService.update(id, request, nutri.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id,
                       @AuthenticationPrincipal Nutricionista nutri) {
        pacienteService.delete(id, nutri.getId());
    }
}