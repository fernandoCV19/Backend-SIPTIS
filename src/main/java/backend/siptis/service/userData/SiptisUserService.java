package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SiptisUserService {

    ServiceAnswer existsById(int id);
    ServiceAnswer existsByEmail(String email);
    ServiceAnswer existsTokenPassword(String tokenPassword);

    ServiceAnswer findById(long id);
    ServiceAnswer findByEmail(String email);
    ServiceAnswer getAllUsers();

    ServiceAnswer getStudentCareerById(Long id);
    ServiceAnswer getTeacherAreasById(Long id);
    ServiceAnswer getTeacherNotSelectedAreasById(Long id);

    Long getIdFromToken(String token);

    ServiceAnswer getUserPersonalInformation(long id);

    SiptisUser save(SiptisUser user);

    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id);

    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);

    ServiceAnswer getPossibleTribunals();

    ServiceAnswer getPersonalActivities(Long id, Pageable pageable);

    ServiceAnswer logIn(LogInDTO logInDTO);

    Optional<SiptisUser> findByTokenPassword(String tokenPassword);

}
