package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectSupervisorService {

    ServiceAnswer getAllProjectsNotAcceptedReviewedByIdSupervisor(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedByIdSupervisor(Long id);

    ServiceAnswer getAllProjectsAcceptedByIsSupervisor(Long id);
}
