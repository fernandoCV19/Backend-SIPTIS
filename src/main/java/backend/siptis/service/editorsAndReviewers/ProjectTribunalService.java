package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.editorsAndReviewers.ReviewADefenseDTO;

public interface ProjectTribunalService {

    ServiceAnswer getAllProjectsNotReviewedByTribunalId(Long id);

    ServiceAnswer getAllProjectsReviewedNotAcceptedByTribunalId(Long id);

    ServiceAnswer getAllProjectsAcceptedWithoutDefensePointsByTribunalId(Long id);

    ServiceAnswer getAllProjectsDefendedByTribunalId(Long id);

    ServiceAnswer acceptProject(Long idTribunal, Long idProject);

    ServiceAnswer removeAcceptProject(Long idTribunal, Long idProject);

    ServiceAnswer reviewADefense(ReviewADefenseDTO reviewADefenseDTO);
}
