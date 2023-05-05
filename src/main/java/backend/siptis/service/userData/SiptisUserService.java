package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.EditTeacherInformationDTO;
import backend.siptis.model.pjo.dto.StudentEditPersonalInfoDTO;
import backend.siptis.model.pjo.dto.TeacherEditPersonalInfoDTO;

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

    ServiceAnswer editStudentInformation(Long userID,EditStudentInformationDTO editDTO);

    ServiceAnswer editTeacherInformation(Long userID, EditTeacherInformationDTO editDTO);

    ServiceAnswer studentEditPersonalInfo(Long id, StudentEditPersonalInfoDTO dto);

    ServiceAnswer teacherEditPersonalInfo(Long id, TeacherEditPersonalInfoDTO dto);
    ServiceAnswer getPersonalActivities(Long id);
}
