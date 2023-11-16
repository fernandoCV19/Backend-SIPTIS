package backend.siptis.service.phaseManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.phaseManagement.Phase;
import backend.siptis.model.pjo.vo.projectManagement.PhaseVO;

public interface PhaseService {

    ServiceAnswer findAllPhases();

    ServiceAnswer findPhaseByUserId(Long idPhase);

    ServiceAnswer findPhaseByModalityId(Long idModality);

    ServiceAnswer getPhasesByUserId(Long idUser);

    PhaseVO entityToVO(Phase phase);
}
