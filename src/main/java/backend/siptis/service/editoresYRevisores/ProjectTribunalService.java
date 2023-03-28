package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTribunalService {

    ServiceAnswer getAllProjectsNotReviewedByIdTribunal(Long id);

    ServiceAnswer getAllProjectsReviewedNotAcceptedByIdTribunal(Long id);

    ServiceAnswer getAllProjectsAcceptedWithoutDefensePointsByIdTribunal(Long id);

    ServiceAnswer getAllProjectsDefendedByIdTribunal(Long id);

}
