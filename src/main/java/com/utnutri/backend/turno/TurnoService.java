package com.utnutri.backend.turno;

import com.utnutri.backend.nutricionista.Nutricionista;
import com.utnutri.backend.paciente.Paciente;
import com.utnutri.backend.paciente.PacienteRepository;
import com.utnutri.backend.turno.dto.TurnoCreateRequest;
import com.utnutri.backend.turno.dto.TurnoDTO;
import com.utnutri.backend.turno.dto.TurnoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;

    // ─── Listar todos los turnos del nutri logueado ──────────────────────────
    public List<TurnoDTO> listarTurnos(Nutricionista nutricionista) {
        return turnoRepository.findAllByNutricionistaId(nutricionista.getId())
                .stream().map(TurnoMapper::toDTO).toList();
    }

    // ─── Próximos turnos para el dashboard ───────────────────────────────────
    public List<TurnoDTO> listarProximos(Nutricionista nutricionista) {
        return turnoRepository.findProximosByNutricionistaId(
                        nutricionista.getId(), LocalDateTime.now())
                .stream().map(TurnoMapper::toDTO).toList();
    }

    // ─── Turnos de un paciente específico ────────────────────────────────────
    public List<TurnoDTO> listarPorPaciente(Long pacienteId, Nutricionista nutricionista) {
        verificarPropiedadPaciente(pacienteId, nutricionista.getId());
        return turnoRepository.findByPacienteId(pacienteId)
                .stream().map(TurnoMapper::toDTO).toList();
    }

    // ─── Obtener uno ─────────────────────────────────────────────────────────
    public TurnoDTO obtenerPorId(Long id, Nutricionista nutricionista) {
        Turno turno = findOwned(id, nutricionista.getId());
        return TurnoMapper.toDTO(turno);
    }

    // ─── Crear ───────────────────────────────────────────────────────────────
    @Transactional
    public TurnoDTO crear(TurnoCreateRequest request, Nutricionista nutricionista) {
        Paciente paciente = verificarPropiedadPaciente(
                request.getPacienteId(), nutricionista.getId());

        Turno turno = Turno.builder()
                .paciente(paciente)
                .fechaHora(request.getFechaHora())
                .observaciones(request.getObservaciones())
                .estado(EstadoTurno.PENDIENTE)
                .build();

        return TurnoMapper.toDTO(turnoRepository.save(turno));
    }

    // ─── Actualizar ──────────────────────────────────────────────────────────
    @Transactional
    public TurnoDTO actualizar(Long id, TurnoUpdateRequest request, Nutricionista nutricionista) {
        Turno turno = findOwned(id, nutricionista.getId());

        if (request.getFechaHora() != null)      turno.setFechaHora(request.getFechaHora());
        if (request.getObservaciones() != null)  turno.setObservaciones(request.getObservaciones());
        if (request.getEstado() != null)         turno.setEstado(request.getEstado());

        return TurnoMapper.toDTO(turnoRepository.save(turno));
    }

    // ─── Eliminar ────────────────────────────────────────────────────────────
    @Transactional
    public void eliminar(Long id, Nutricionista nutricionista) {
        Turno turno = findOwned(id, nutricionista.getId());
        turnoRepository.delete(turno);
    }

    // ─── Verifica que el paciente pertenezca al nutri logueado ───────────────
    private Paciente verificarPropiedadPaciente(Long pacienteId, Long nutricionistaId) {
        return pacienteRepository.findByIdAndNutricionistaId(pacienteId, nutricionistaId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Paciente no encontrado con id: " + pacienteId));
    }

    // ─── Helper: busca el turno verificando que sea del nutri ────────────────
    private Turno findOwned(Long id, Long nutricionistaId) {
        return turnoRepository.findByIdAndNutricionistaId(id, nutricionistaId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Turno no encontrado con id: " + id));
    }
}