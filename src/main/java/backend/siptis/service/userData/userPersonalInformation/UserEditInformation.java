package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.TeacherEditPersonalInfoDTO;

public interface UserEditInformation {

    ServiceAnswer teacherEditPersonalInfo(Long id, TeacherEditPersonalInfoDTO dto);

}
