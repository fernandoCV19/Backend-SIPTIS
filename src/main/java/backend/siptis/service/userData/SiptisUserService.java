package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.pjo.dto.userDataDTO.*;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.*;
import org.springframework.data.domain.Pageable;

public interface SiptisUserService {

    ServiceAnswer logIn(LogInDTO logInDTO);


    ServiceAnswer registerAdmin(RegisterAdminDTO dto);
    ServiceAnswer registerStudent(RegisterStudentDTO dto);
    ServiceAnswer registerUser(RegisterUserDTO dto);

    ServiceAnswer deleteUser(Long id);

    ServiceAnswer getUserById(Long id);
    ServiceAnswer getUserByEmail(String email);
    Long getIdFromToken(String token);
    ServiceAnswer getUserPersonalInformation(Long id);
    ServiceAnswer getStudentCareerById(Long id);
    ServiceAnswer getUserAreasById(Long id);
    ServiceAnswer getUserNotSelectedAreasById(Long id);

    ServiceAnswer getUserList(String search,String role, Pageable pageable);
    ServiceAnswer getNormalUserList(String search, Pageable pageable);

    ServiceAnswer getRoles(Long id);

    ServiceAnswer updateRoles(Long id, RolesListDTO dto);

    ServiceAnswer getAllUsers();

    ServiceAnswer updateToken(String refreshToken);

    ServiceAnswer getPossibleTribunals();

    ServiceAnswer userEditPersonalInformation(Long id, UserEditInformationDTO dto);

    ServiceAnswer adminEditUserPersonalInformation(Long id, UniversityUserPersonalInformationDTO dto);
    ServiceAnswer adminEditSpecialUserPersonalInformation(Long id, GeneralUserPersonalInformationDTO dto);

    ServiceAnswer updateAreas(Long id, UserSelectedAreasDTO dto);

    ServiceAnswer existsTokenPassword(String tokenPassword);
    SiptisUser findByTokenPassword(String tokenPassword);


    Long getProjectById(Long id);

    ServiceAnswer getPersonalActivities(Long id, Pageable pageable);
}
