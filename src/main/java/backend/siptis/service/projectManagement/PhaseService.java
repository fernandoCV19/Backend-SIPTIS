package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.PhaseDTO;

public interface PhaseService {
    ServiceAnswer createPhase(PhaseDTO phaseDTO);

    ServiceAnswer deletePhase(Long idPhase);

    ServiceAnswer updatePhase(PhaseDTO phaseDTO, Long idPhase);

    ServiceAnswer findAllPhases();

    ServiceAnswer findPhaseById(Long idPhase);
}
