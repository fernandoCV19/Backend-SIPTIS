package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;

public interface UserAuthService {
    ServiceAnswer findAll();

    ServiceAnswer registerStudent(StudentRegisterDTO estudianteDTO);

    ServiceAnswer registerAdmin(AdminRegisterDTO adminDTO);


}
