package com.utnutri.backend.paciente;

import com.utnutri.backend.nutricionista.Nutricionista;
import com.utnutri.backend.paciente.dto.PacienteCreateRequest;
import com.utnutri.backend.paciente.dto.PacienteDTO;
import com.utnutri.backend.paciente.dto.PacienteUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    // ─── Listar todos los pacientes del nutri logueado ───────────────────────
    public List<PacienteDTO> getAll(Long nutriId) {
        return pacienteRepository.findByNutricionistaId(nutriId)
                .stream()
                .map(PacienteMapper::toDTO)
                .toList();
    }

    // ─── Obtener uno (verificando que sea del nutri logueado) ─────────────────
    public PacienteDTO getById(Long id, Long nutriId) {
        Paciente paciente = findOwned(id, nutriId);
        return PacienteMapper.toDTO(paciente);
    }

    // ─── Crear ───────────────────────────────────────────────────────────────
    public PacienteDTO create(PacienteCreateRequest request, Nutricionista nutri) {
        Paciente paciente = Paciente.builder()
                .nutricionista(nutri)
                .nombre(request.getNombre())
                .genero(request.getGenero())
                .fechaNacimiento(request.getFechaNacimiento())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .build();

        return PacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    // ─── Actualizar ──────────────────────────────────────────────────────────
    public PacienteDTO update(Long id, PacienteUpdateRequest request, Long nutriId) {
        Paciente paciente = findOwned(id, nutriId);

        if (request.getNombre() != null)          paciente.setNombre(request.getNombre());
        if (request.getGenero() != null)          paciente.setGenero(request.getGenero());
        if (request.getFechaNacimiento() != null) paciente.setFechaNacimiento(request.getFechaNacimiento());
        if (request.getCorreo() != null)          paciente.setCorreo(request.getCorreo());
        if (request.getTelefono() != null)        paciente.setTelefono(request.getTelefono());

        return PacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    // ─── Eliminar ────────────────────────────────────────────────────────────
    public void delete(Long id, Long nutriId) {
        Paciente paciente = findOwned(id, nutriId);
        pacienteRepository.delete(paciente);
    }

    // ─── Helper: busca el paciente Y valida que pertenezca al nutri ──────────
    private Paciente findOwned(Long id, Long nutriId) {
        return pacienteRepository.findByIdAndNutricionistaId(id, nutriId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Paciente no encontrado con id: " + id));
    }
}