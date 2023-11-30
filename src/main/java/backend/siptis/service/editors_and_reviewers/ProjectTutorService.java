package backend.siptis.service.editors_and_reviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTutorService {
    ServiceAnswer getAllProjectsNotAcceptedReviewedByTutorId(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTutorId(Long id);

    ServiceAnswer getAllProjectsAcceptedByTutorId(Long id);

    ServiceAnswer acceptProject(Long idTutor, Long idProject);
}
