package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SiptisUserService {

    ServiceAnswer logIn(LogInDTO logInDTO);
    ServiceAnswer getAllUsers();

    ServiceAnswer getUserById(Long id);
    ServiceAnswer getUserByEmail(String email);

    ServiceAnswer registerTeacher(RegisterUserDTO dto);
    ServiceAnswer registerAdmin(AdminRegisterDTO dto);
    ServiceAnswer registerStudent(RegisterStudentDTO dto);

    ServiceAnswer getStudentList(String search,Pageable pageable);
    ServiceAnswer getTeacherList(String search,Pageable pageable);
    ServiceAnswer getAdminList(Pageable pageable);

    ServiceAnswer deleteUser(Long id);
    ServiceAnswer getUserPersonalInformation(Long id);

    ServiceAnswer updateToken(String refreshToken);
    Long getIdFromToken(String token);

    ServiceAnswer getTeacherAreasById(Long id);
    ServiceAnswer getTeacherNotSelectedAreasById(Long id);

    ServiceAnswer userEditPersonalInformation();

    ServiceAnswer updateAreas(Long id, UserSelectedAreasDTO dto);

    ServiceAnswer getStudentCareerById(Long id);

    ServiceAnswer getPossibleTribunals();
    /*
    ServiceAnswer existsById(int id);
    ServiceAnswer existsByEmail(String email);
    ServiceAnswer existsTokenPassword(String tokenPassword);

    ServiceAnswer findById(long id);
    ServiceAnswer findByEmail(String email);
    ServiceAnswer getAllUsers();

    ServiceAnswer getUserPersonalInformation(long id);

    SiptisUser save(SiptisUser user);

    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id);

    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);



    ServiceAnswer adminEditUserPersonalInformation();

    ServiceAnswer getUserPersonalInformation(Long id);
    ServiceAnswer getUserStudentPersonalInformation(Long id);
    ServiceAnswer getPersonalActivities(Long id, Pageable pageable);



    Optional<SiptisUser> findByTokenPassword(String tokenPassword);
*/
}
