package com.utnutri.backend.plan;

import com.utnutri.backend.paciente.Paciente;
import com.utnutri.backend.paciente.PacienteRepository;
import com.utnutri.backend.plan.dto.PlanNutricionalCreateRequest;
import com.utnutri.backend.plan.dto.PlanNutricionalDTO;
import com.utnutri.backend.plan.dto.PlanNutricionalUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PlanNutricionalService {

    private final PlanNutricionalRepository planNutricionalRepository;
    private final PacienteRepository pacienteRepository;

    // ─── Obtener el plan del paciente ────────────────────────────────────────
    public PlanNutricionalDTO get(Long pacienteId, Long nutriId) {
        verificarPropiedadPaciente(pacienteId, nutriId);
        PlanNutricional plan = findPlan(pacienteId);
        return PlanNutricionalMapper.toDTO(plan);
    }

    // ─── Crear ───────────────────────────────────────────────────────────────
    public PlanNutricionalDTO create(Long pacienteId, PlanNutricionalCreateRequest request, Long nutriId) {
        Paciente paciente = verificarPropiedadPaciente(pacienteId, nutriId);

        if (planNutricionalRepository.findByPacienteId(pacienteId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El paciente ya tiene un plan nutricional asignado");
        }

        PlanNutricional plan = PlanNutricional.builder()
                .paciente(paciente)
                .desayuno(request.getDesayuno())
                .almuerzo(request.getAlmuerzo())
                .merienda(request.getMerienda())
                .cena(request.getCena())
                .snacks(request.getSnacks())
                .notas(request.getNotas())
                .build();

        return PlanNutricionalMapper.toDTO(planNutricionalRepository.save(plan));
    }

    // ─── Actualizar ──────────────────────────────────────────────────────────
    public PlanNutricionalDTO update(Long pacienteId, PlanNutricionalUpdateRequest request, Long nutriId) {
        verificarPropiedadPaciente(pacienteId, nutriId);
        PlanNutricional plan = findPlan(pacienteId);

        if (request.getDesayuno() != null)  plan.setDesayuno(request.getDesayuno());
        if (request.getAlmuerzo() != null)  plan.setAlmuerzo(request.getAlmuerzo());
        if (request.getMerienda() != null)  plan.setMerienda(request.getMerienda());
        if (request.getCena() != null)      plan.setCena(request.getCena());
        if (request.getSnacks() != null)    plan.setSnacks(request.getSnacks());
        if (request.getNotas() != null)     plan.setNotas(request.getNotas());

        return PlanNutricionalMapper.toDTO(planNutricionalRepository.save(plan));
    }

    // ─── Eliminar ────────────────────────────────────────────────────────────
    public void delete(Long pacienteId, Long nutriId) {
        verificarPropiedadPaciente(pacienteId, nutriId);
        PlanNutricional plan = findPlan(pacienteId);
        planNutricionalRepository.delete(plan);
    }

    // ─── Verifica que el paciente pertenezca al nutri logueado ───────────────
    private Paciente verificarPropiedadPaciente(Long pacienteId, Long nutriId) {
        return pacienteRepository.findByIdAndNutricionistaId(pacienteId, nutriId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Paciente no encontrado con id: " + pacienteId));
    }

    // ─── Helper: busca el plan o lanza 404 ───────────────────────────────────
    private PlanNutricional findPlan(Long pacienteId) {
        return planNutricionalRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El paciente no tiene un plan nutricional asignado"));
    }
}