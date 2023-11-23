package backend.siptis.service.phase_management;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.phase_management.Phase;
import backend.siptis.model.pjo.vo.project_management.PhaseVO;

public interface PhaseService {

    ServiceAnswer findAllPhases();

    ServiceAnswer findPhaseByUserId(Long idPhase);

    ServiceAnswer findPhaseByModalityId(Long idModality);

    ServiceAnswer getPhasesByUserId(Long idUser);

    PhaseVO entityToVO(Phase phase);
}
