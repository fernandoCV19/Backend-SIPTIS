package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectService {

    ServiceAnswer getProjects();

    ServiceAnswer getPresentations (Long idProyecto);

}
