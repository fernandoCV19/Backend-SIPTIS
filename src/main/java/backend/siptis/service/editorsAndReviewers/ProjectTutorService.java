package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTutorService {
    ServiceAnswer getAllProjectsNotAcceptedReviewedByTutorId(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTutorId(Long id);

    ServiceAnswer getAllProjectsAcceptedByTutorId(Long id);

    ServiceAnswer acceptProject(Long idTutor, Long idProject);

    ServiceAnswer removeAcceptProject(Long idTutor, Long idProject);

    ServiceAnswer getAllTutorProject(Long idTutor);
}
