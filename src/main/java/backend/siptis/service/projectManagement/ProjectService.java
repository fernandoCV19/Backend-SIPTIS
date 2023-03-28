package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectService {

    ServiceAnswer obtenerProyectos();

    ServiceAnswer obtenerPresentaciones (Long idProyecto);

}
