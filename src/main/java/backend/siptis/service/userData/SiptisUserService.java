package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;

public interface SiptisUserService {

    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id);

    ServiceAnswer getAllUsers();

    ServiceAnswer findAll();
    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);



    SiptisUser findByEmail(String email);

    SiptisUser findByTokenPassword(String email);

    SiptisUser save(SiptisUser user);

    boolean existsByEmail(String email);

    boolean existsTokenPassword(String tokenPassword);

    ServiceAnswer getPersonalActivities(Long id);
}
