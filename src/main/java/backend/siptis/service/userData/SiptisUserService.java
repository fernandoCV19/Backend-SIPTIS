package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentEditPersonalInfoDTO;

public interface SiptisUserService {



    ServiceAnswer findAll();
    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);
    ServiceAnswer getAllUsers();

    SiptisUser findByEmail(String email);

    SiptisUser findByTokenPassword(String email);

    SiptisUser save(SiptisUser user);

    ServiceAnswer editStudentInformation(Long userID,EditStudentInformationDTO editDTO);

    ServiceAnswer studentEditPersonalInfo(Long id, StudentEditPersonalInfoDTO dto);
}
