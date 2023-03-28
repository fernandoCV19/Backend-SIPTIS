package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTeacherService {

    ServiceAnswer getAllProjectsNotAcceptedReviewedByTeacherId(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTeacherId(Long id);

    ServiceAnswer getAllProjectsAcceptedByTeacherId(Long id);
}
