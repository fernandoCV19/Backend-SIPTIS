package backend.siptis.service.editors_and_reviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectSupervisorService {

    ServiceAnswer getAllProjectsNotAcceptedReviewedBySupervisorId(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedBySupervisorId(Long id);

    ServiceAnswer getAllProjectsAcceptedBySupervisorId(Long id);

    ServiceAnswer acceptProject(Long idSupervisor, Long idProject);
}
