package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.projectManagement.AssignTribunalsDTO;

public interface ProjectService {

    ServiceAnswer getProjects();

    ServiceAnswer getPresentations (Long idProyecto);

    ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer);

    ServiceAnswer getAllProjectInfo(Long idProject);

    ServiceAnswer getProjectInfoToAssignTribunals(Long idProject);

    ServiceAnswer assignTribunals(AssignTribunalsDTO assignTribunalsDTO);
}
