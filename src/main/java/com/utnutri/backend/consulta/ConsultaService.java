package com.utnutri.backend.consulta;

import com.utnutri.backend.consulta.dto.ConsultaCreateRequest;
import com.utnutri.backend.consulta.dto.ConsultaDTO;
import com.utnutri.backend.consulta.dto.ConsultaUpdateRequest;
import com.utnutri.backend.paciente.Paciente;
import com.utnutri.backend.paciente.PacienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.ContinueResponseTiming;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    // ─── Listar todas las consultas de un paciente ───────────────────────

    public List<ConsultaDTO> getAll(Long idPaciente, Long nutriId) {
        verificarPropiedadPaciente(idPaciente,  nutriId);

        return consultaRepository.findByPacienteId(idPaciente)
                .stream()
                .map(ConsultaMapper::toDTO)
                .toList();
    }

    // ─── Obtener una consulta verificando que sea del nutri logueado y que la consulta pertenezca al paciente ─────────────────
    public ConsultaDTO getById(Long pacienteId, Long id, Long nutriId) {
        verificarPropiedadPaciente(pacienteId, nutriId);
        Consulta consulta = findOwned(id, pacienteId);
        return ConsultaMapper.toDTO(consulta);
    }

    // ─── Crear ───────────────────────────────────────────────────────────────
    public ConsultaDTO create(Long pacienteId, ConsultaCreateRequest request,Long nutriId) {
        Paciente paciente = verificarPropiedadPaciente(pacienteId, nutriId);

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .fecha(request.getFecha())
                .peso(request.getPeso())
                .altura(request.getAltura())
                .grasa(request.getGrasa())
                .masa(request.getMasa())
                .observaciones(request.getObservaciones())
                .build();

        return ConsultaMapper.toDTO(consultaRepository.save(consulta));
    }


    // ─── Actualizar ──────────────────────────────────────────────────────────
    public ConsultaDTO update(Long pacienteId, Long id, ConsultaUpdateRequest request, Long nutriId) {
        verificarPropiedadPaciente(pacienteId, nutriId);
        Consulta consulta = findOwned(id, pacienteId);

        if (request.getFecha() != null)         consulta.setFecha(request.getFecha());
        if (request.getPeso() != null)          consulta.setPeso(request.getPeso());
        if (request.getAltura() != null)        consulta.setAltura(request.getAltura());
        if (request.getGrasa() != null)         consulta.setGrasa(request.getGrasa());
        if (request.getMasa() != null)          consulta.setMasa(request.getMasa());
        if (request.getObservaciones() != null) consulta.setObservaciones(request.getObservaciones());

        return ConsultaMapper.toDTO(consultaRepository.save(consulta));

    }

    // ─── Eliminar ────────────────────────────────────────────────────────────
    public void delete(Long pacienteId, Long id, Long nutriId) {
        verificarPropiedadPaciente(pacienteId, nutriId);
        Consulta consulta = findOwned(id, pacienteId);
        consultaRepository.delete(consulta);
    }


    // ─── Verifica que el paciente pertenezca al nutri logueado ───────────────
    private Paciente verificarPropiedadPaciente(Long pacienteId, Long nutriId) {
        return pacienteRepository.findByIdAndNutricionistaId(pacienteId, nutriId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Paciente no encontrado con id: " + pacienteId));
    }

    // ─── Helper: verifica que la consulta pertenezca al paciente ──────────
    private Consulta findOwned(Long id, Long pacienteId) {
        return consultaRepository.findByIdAndPacienteId(id, pacienteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Consulta no encontrada con id: " + id));
    }
}


