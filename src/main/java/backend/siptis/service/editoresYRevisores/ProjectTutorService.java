package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTutorService {
    ServiceAnswer getAllProjectsNotAcceptedReviewedByIdTutor(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedByIdTutor(Long id);

    ServiceAnswer getAllProjectsAcceptedByIdTutor(Long id);
}
