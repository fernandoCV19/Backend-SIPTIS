package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.AdminEditUserPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;

public interface EditPersonalInformationService {


    ServiceAnswer EditLimitedPersonalInformationById(Long id, UserEditPersonalInformationDTO dto);

    ServiceAnswer UpdateUserAreas(Long id, UserSelectedAreasDTO dto);

    ServiceAnswer UpdateStudentCareer(Long id, Long careerId);

    ServiceAnswer EditFullPersonalInformationById(Long id, AdminEditUserPersonalInformationDTO dto);

}
