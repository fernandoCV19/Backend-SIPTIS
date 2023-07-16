package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.EditTeacherInformationDTO;
import backend.siptis.model.pjo.dto.StudentEditPersonalInfoDTO;
import backend.siptis.model.pjo.dto.TeacherEditPersonalInfoDTO;
import org.springframework.data.domain.Pageable;

public interface SiptisUserService {

    boolean existsByEmail(String email);

    boolean existsTokenPassword(String tokenPassword);

    SiptisUser findByEmail(String email);

    SiptisUser findByTokenPassword(String email);

    ServiceAnswer getAllUsers();

    ServiceAnswer findAll();



    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id);

    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);

    SiptisUser save(SiptisUser user);


    ServiceAnswer getPossibleTribunals();
    ServiceAnswer getPersonalActivities(Long id, Pageable pageable);
}
