package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.AdminEditUserPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;

public interface PersonalInformation {

    ServiceAnswer getPersonalInformationById(Long id);

    ServiceAnswer UserEditPersonalInformationById(Long id, UserEditPersonalInformationDTO dto);

    ServiceAnswer AdminEditPersonalInformationById(Long id, AdminEditUserPersonalInformationDTO dto);
}
