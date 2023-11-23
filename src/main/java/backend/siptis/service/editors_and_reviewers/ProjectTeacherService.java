package backend.siptis.service.editors_and_reviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectTeacherService {

    ServiceAnswer getAllProjectsNotAcceptedReviewedByTeacherId(Long id);

    ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTeacherId(Long id);

    ServiceAnswer getAllProjectsAcceptedByTeacherId(Long id);

    ServiceAnswer acceptProject(Long idTeacher, Long idProject);

}
