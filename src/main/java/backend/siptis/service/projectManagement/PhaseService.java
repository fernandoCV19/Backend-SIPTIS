package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.projectManagement.Phase;
import backend.siptis.model.pjo.dto.PhaseDTO;
import backend.siptis.model.pjo.vo.projectManagement.PhaseVO;

public interface PhaseService {
    ServiceAnswer createPhase(PhaseDTO phaseDTO);

    ServiceAnswer deletePhase(Long idPhase);

    ServiceAnswer updatePhase(PhaseDTO phaseDTO, Long idPhase);

    ServiceAnswer findAllPhases();

    ServiceAnswer findPhaseByUserId(Long idPhase);

    ServiceAnswer findPhaseByModalityId(Long idModality);

    ServiceAnswer getPhasesByUserId(Long idUser);

    PhaseVO entityToVO(Phase phase);
}
