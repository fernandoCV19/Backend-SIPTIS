package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectSupervisorService {

    ServiceAnswer getAllProjectsNotAcceptedReviewedBySupervisorId(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedBySupervisorId(Long id);

    ServiceAnswer getAllProjectsAcceptedBySupervisorId(Long id);

    ServiceAnswer acceptProject(Long idSupervisor, Long idProject);

    ServiceAnswer removeAcceptProject(Long idSupervisor, Long idProject);
}
