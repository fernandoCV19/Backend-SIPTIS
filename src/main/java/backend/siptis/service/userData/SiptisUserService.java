package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;

public interface SiptisUserService {



    ServiceAnswer findAll();
    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);

}
