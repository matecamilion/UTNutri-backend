package com.utnutri.backend.plan;

import com.utnutri.backend.nutricionista.Nutricionista;
import com.utnutri.backend.plan.dto.PlanNutricionalRequest;
import com.utnutri.backend.plan.dto.PlanNutricionalDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes/{pacienteId}/plan")
@RequiredArgsConstructor
public class PlanNutricionalController {

    private final PlanNutricionalService planNutricionalService;

    @GetMapping
    public PlanNutricionalDTO get(@PathVariable Long pacienteId,
                                  @AuthenticationPrincipal Nutricionista nutri) {
        return planNutricionalService.get(pacienteId, nutri.getId());
    }

    @PutMapping
    public PlanNutricionalDTO upsert(@PathVariable Long pacienteId,
                                     @Valid @RequestBody PlanNutricionalRequest request,
                                     @AuthenticationPrincipal Nutricionista nutri) {
        return planNutricionalService.upsert(pacienteId, request, nutri.getId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long pacienteId,
                       @AuthenticationPrincipal Nutricionista nutri) {
        planNutricionalService.delete(pacienteId, nutri.getId());
    }
}