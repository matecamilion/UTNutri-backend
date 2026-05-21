package com.utnutri.backend.plan;

import com.utnutri.backend.paciente.Paciente;
import com.utnutri.backend.paciente.PacienteRepository;
import com.utnutri.backend.plan.dto.PlanNutricionalRequest;
import com.utnutri.backend.plan.dto.PlanNutricionalDTO;
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

    public PlanNutricionalDTO upsert(Long pacienteId, PlanNutricionalRequest request, Long nutriId) {
        Paciente paciente = verificarPropiedadPaciente(pacienteId, nutriId);

        // Busca el plan existente, o crea uno nuevo si no hay
        PlanNutricional plan = planNutricionalRepository.findByPacienteId(pacienteId)
                .orElseGet(() -> PlanNutricional.builder().paciente(paciente).build());

        // Setea todos los campos
        plan.setDesayuno(request.getDesayuno());
        plan.setAlmuerzo(request.getAlmuerzo());
        plan.setMerienda(request.getMerienda());
        plan.setCena(request.getCena());
        plan.setSnacks(request.getSnacks());
        plan.setNotas(request.getNotas());

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