package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTutorService {
    ServiceAnswer getAllProjectsNotAcceptedReviewedByTutorId(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTutorId(Long id);

    ServiceAnswer getAllProjectsAcceptedByTutorId(Long id);
}