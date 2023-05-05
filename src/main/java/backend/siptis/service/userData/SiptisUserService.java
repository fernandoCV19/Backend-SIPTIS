package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentEditPersonalInfoDTO;

public interface SiptisUserService {

    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id);

    ServiceAnswer getAllUsers();

    ServiceAnswer findAll();
    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);
    ServiceAnswer getAllUsers();

    ServiceAnswer editStudentInformation(Long userID,EditStudentInformationDTO editDTO);

    ServiceAnswer studentEditPersonalInfo(Long id, StudentEditPersonalInfoDTO dto);
    ServiceAnswer getPersonalActivities(Long id);
}
