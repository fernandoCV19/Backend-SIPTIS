package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectService {

    ServiceAnswer getProjects();

    ServiceAnswer getPresentations (Long idProyecto);

    ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer);

    ServiceAnswer getAllProjectInfo(Long idProject);

    ServiceAnswer getProjectInfoToAssignTribunals(Long idProject);
}
