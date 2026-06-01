package com.utnutri.backend.turno;

import com.utnutri.backend.common.exception.ResourceNotFoundException;
import com.utnutri.backend.nutricionista.Nutricionista;
import com.utnutri.backend.paciente.Paciente;
import com.utnutri.backend.paciente.PacienteRepository;
import com.utnutri.backend.turno.dto.TurnoCreateRequest;
import com.utnutri.backend.turno.dto.TurnoDTO;
import com.utnutri.backend.turno.dto.TurnoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;

    private TurnoDTO toDTO(Turno t) {
        return new TurnoDTO(
                t.getId(),
                t.getPaciente().getId(),
                t.getPaciente().getNombre(),
                t.getFechaHora(),
                t.getObservaciones(),
                t.getEstado(),
                t.getCreatedAt()
        );
    }

    private Paciente validarPacienteDelNutricionista(Long pacienteId, Long nutricionistaId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        if (!paciente.getNutricionista().getId().equals(nutricionistaId)) {
            throw new AccessDeniedException("No tenés permiso sobre este paciente");
        }
        return paciente;
    }

    public List<TurnoDTO> listarTurnos(Nutricionista nutricionista) {
        return turnoRepository.findAllByNutricionistaId(nutricionista.getId())
                .stream().map(this::toDTO).toList();
    }

    public List<TurnoDTO> listarProximos(Nutricionista nutricionista) {
        return turnoRepository.findProximosByNutricionistaId(
                        nutricionista.getId(), LocalDateTime.now())
                .stream().map(this::toDTO).toList();
    }

    public List<TurnoDTO> listarPorPaciente(Long pacienteId, Nutricionista nutricionista) {
        validarPacienteDelNutricionista(pacienteId, nutricionista.getId());
        return turnoRepository.findByPacienteId(pacienteId)
                .stream().map(this::toDTO).toList();
    }

    public TurnoDTO obtenerPorId(Long id, Nutricionista nutricionista) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));

        if (!turno.getPaciente().getNutricionista().getId().equals(nutricionista.getId())) {
            throw new AccessDeniedException("No tenés permiso sobre este turno");
        }
        return toDTO(turno);
    }

    @Transactional
    public TurnoDTO crear(TurnoCreateRequest request, Nutricionista nutricionista) {
        Paciente paciente = validarPacienteDelNutricionista(
                request.getPacienteId(), nutricionista.getId());

        Turno turno = Turno.builder()
                .paciente(paciente)
                .fechaHora(request.getFechaHora())
                .observaciones(request.getObservaciones())
                .estado(EstadoTurno.PENDIENTE)
                .build();

        return toDTO(turnoRepository.save(turno));
    }

    @Transactional
    public TurnoDTO actualizar(Long id, TurnoUpdateRequest request, Nutricionista nutricionista) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));

        if (!turno.getPaciente().getNutricionista().getId().equals(nutricionista.getId())) {
            throw new AccessDeniedException("No tenés permiso sobre este turno");
        }

        if (request.getFechaHora() != null)      turno.setFechaHora(request.getFechaHora());
        if (request.getObservaciones() != null)  turno.setObservaciones(request.getObservaciones());
        if (request.getEstado() != null)         turno.setEstado(request.getEstado());

        return toDTO(turnoRepository.save(turno));
    }

    @Transactional
    public void eliminar(Long id, Nutricionista nutricionista) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));

        if (!turno.getPaciente().getNutricionista().getId().equals(nutricionista.getId())) {
            throw new AccessDeniedException("No tenés permiso sobre este turno");
        }

        turnoRepository.delete(turno);
    }
}