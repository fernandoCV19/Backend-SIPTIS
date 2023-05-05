package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTeacherService {

    ServiceAnswer getAllProjectsNotAcceptedReviewedByTeacherId(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTeacherId(Long id);

    ServiceAnswer getAllProjectsAcceptedByTeacherId(Long id);

    ServiceAnswer acceptProject(Long idTeacher, Long idProject);

    ServiceAnswer removeAcceptProject(Long idTeacher, Long idProject);
}
