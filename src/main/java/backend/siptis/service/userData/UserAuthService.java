package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;

public interface UserAuthService {
    ServiceAnswer findAll();

    ServiceAnswer registerStudent(StudentRegisterDTO estudianteDTO);

    ServiceAnswer registerAdmin(AdminRegisterDTO adminDTO);

    ServiceAnswer logIn(LogInDTO logInDTO);



}
