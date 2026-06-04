package com.utnutri.backend.nutricionista;

import com.utnutri.backend.nutricionista.dto.NutricionistaCreateRequest;
import com.utnutri.backend.nutricionista.dto.NutricionistaDTO;
import com.utnutri.backend.nutricionista.dto.NutricionistaUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutricionistas")
@RequiredArgsConstructor
public class NutricionistaController {

    private final NutricionistaService nutricionistaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NutricionistaDTO create(@Valid @RequestBody NutricionistaCreateRequest request) {
        return nutricionistaService.create(request);
    }
    @GetMapping
    public List<NutricionistaDTO> getAll() {
        return nutricionistaService.getAll();
    }

    @GetMapping("/{id}")
    public NutricionistaDTO getById(@PathVariable Long id) {
        return nutricionistaService.getById(id);
    }

    @PutMapping("/{id}")
    public NutricionistaDTO update(@PathVariable Long id,
                                   @Valid @RequestBody NutricionistaUpdateRequest request) {
        return nutricionistaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        nutricionistaService.delete(id);
    }
}