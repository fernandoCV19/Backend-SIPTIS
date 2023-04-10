package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;

public interface SiptisUserService {



    ServiceAnswer findAll();
    ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id);

    ServiceAnswer editStudentInformation(Long userID,EditStudentInformationDTO editDTO);
}
