package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.pjo.dto.userDataDTO.*;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface SiptisUserService {

    ServiceAnswer logIn(LogInDTO logInDTO);


    ServiceAnswer registerAdmin(RegisterAdminDTO dto);

    ServiceAnswer registerStudent(RegisterStudentDTO dto);

    ServiceAnswer registerUser(RegisterUserDTO dto);

    ServiceAnswer deleteUser(Long id);

    Long getIdFromToken(String token);

    ServiceAnswer getUserPersonalInformation(Long id);

    ServiceAnswer getStudentCareerById(Long id);

    ServiceAnswer getUserAreasById(Long id);

    ServiceAnswer getUserList(String search, String role, Pageable pageable);

    ServiceAnswer getNormalUserList(String search, Pageable pageable);

    ServiceAnswer getAdminUserList(String search, Pageable pageable);

    ServiceAnswer getRolesById(Long id);

    ServiceAnswer updateRoles(Long id, RolesListDTO dto);

    ServiceAnswer getAllUsers();

    ServiceAnswer updateToken(String refreshToken);

    ServiceAnswer getPossibleTribunals();

    ArrayList<?> getProjectsFromToken(String token);

    ServiceAnswer userEditPersonalInformation(Long id, UserEditInformationDTO dto);

    ServiceAnswer adminEditUserInformation(Long id, AdminEditUserInformationDTO dto);

    ServiceAnswer updateAreas(Long id, UserSelectedAreasDTO dto);

    ServiceAnswer existsTokenPassword(String tokenPassword);

    SiptisUser findByTokenPassword(String tokenPassword);

    ServiceAnswer registerUserAsCareerDirector(Long id, String directorRole);

    ServiceAnswer getDirectorPersonalInformation(String directorRole);

    ServiceAnswer removeDirectorRole(String directorRole);

    boolean existCareerDirector(String directorRole);

    String getCareerDirectorName(String career);

    ServiceAnswer getNumberStudentsCareer(Long careerId);

    ServiceAnswer getNumberOfStudentsByYearAndCareer(Long careerId);


    Long getProjectById(Long id);

    ServiceAnswer getPersonalActivities(Long id, Pageable pageable);
}
