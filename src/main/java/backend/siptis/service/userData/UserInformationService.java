package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;

public interface UserInformationService {

    ServiceAnswer findAll();

    ServiceAnswer registerStudent(
            StudentRegisterDTO estudianteDTO, SiptisUser siptisUser);

}
