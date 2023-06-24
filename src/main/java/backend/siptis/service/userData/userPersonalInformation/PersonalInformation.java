package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.pjo.dto.AdminEditUserPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;

import java.util.ArrayList;
import java.util.List;

public interface PersonalInformation {

    ServiceAnswer getPersonalInformationById(Long id);

    ServiceAnswer UserEditPersonalInformationById(Long id, UserEditPersonalInformationDTO dto);

    ServiceAnswer UserRegisterAreas(Long id, UserSelectedAreasDTO dto);

    ServiceAnswer AdminEditPersonalInformationById(Long id, AdminEditUserPersonalInformationDTO dto);

    ServiceAnswer getStudentCareerById(Long id);

    ServiceAnswer getTeacherAreasById(Long id);

}
