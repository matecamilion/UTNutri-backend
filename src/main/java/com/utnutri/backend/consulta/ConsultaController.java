package com.utnutri.backend.consulta;

import com.utnutri.backend.consulta.dto.ConsultaCreateRequest;
import com.utnutri.backend.consulta.dto.ConsultaDTO;
import com.utnutri.backend.consulta.dto.ConsultaUpdateRequest;
import com.utnutri.backend.nutricionista.Nutricionista;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes/{pacienteId}/consultas")
@RequiredArgsConstructor
public class ConsultaController {
    private final ConsultaService consultaService;

    @GetMapping
    public List<ConsultaDTO> getAll(@PathVariable Long pacienteId,
                                    @AuthenticationPrincipal Nutricionista nutri) {
        return consultaService.getAll(pacienteId, nutri.getId());
    }

    @GetMapping("/{idConsulta}")
    public ConsultaDTO getById(@PathVariable Long pacienteId,
                                     @AuthenticationPrincipal Nutricionista nutri,
                                     @PathVariable Long idConsulta) {
        return consultaService.getById(pacienteId, idConsulta, nutri.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaDTO create(@PathVariable Long pacienteId,
                              @Valid @RequestBody ConsultaCreateRequest request,
                              @AuthenticationPrincipal Nutricionista nutri){
        return consultaService.create(pacienteId, request, nutri.getId());
    }

    @PutMapping("/{idConsulta}")
    public ConsultaDTO update(@PathVariable Long pacienteId,
                              @PathVariable Long idConsulta,
                              @Valid @RequestBody ConsultaUpdateRequest request,
                              @AuthenticationPrincipal Nutricionista nutri){
        return consultaService.update(pacienteId, idConsulta, request, nutri.getId());
    }

    @DeleteMapping("/{idConsulta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long pacienteId,
                       @PathVariable Long idConsulta,
                       @AuthenticationPrincipal Nutricionista nutri) {
        consultaService.delete(pacienteId, idConsulta, nutri.getId());
    }

}
