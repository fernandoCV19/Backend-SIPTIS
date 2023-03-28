package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTribunalService {

    ServiceAnswer getAllProjectsNotReviewedByTribunalId(Long id);

    ServiceAnswer getAllProjectsReviewedNotAcceptedByTribunalId(Long id);

    ServiceAnswer getAllProjectsAcceptedWithoutDefensePointsByTribunalId(Long id);

    ServiceAnswer getAllProjectsDefendedByTribunalId(Long id);

}
