package com.utnutri.backend.nutricionista;

import com.utnutri.backend.nutricionista.dto.NutricionistaDTO;
import com.utnutri.backend.nutricionista.dto.NutricionistaUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nutricionistas")
@RequiredArgsConstructor
public class NutricionistaController {

    private final NutricionistaService nutricionistaService;

    @GetMapping("/perfil")
    public NutricionistaDTO getPerfil(@AuthenticationPrincipal Nutricionista nutri) {
        return nutricionistaService.getById(nutri.getId());
    }

    @PutMapping("/perfil")
    public NutricionistaDTO updatePerfil(@Valid @RequestBody NutricionistaUpdateRequest request,
                                         @AuthenticationPrincipal Nutricionista nutri) {
        return nutricionistaService.update(nutri.getId(), request);
    }
}
