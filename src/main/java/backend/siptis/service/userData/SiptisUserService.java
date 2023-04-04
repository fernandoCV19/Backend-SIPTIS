package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;

public interface SiptisUserService {

    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);
    ServiceAnswer getAllUsers();
}
