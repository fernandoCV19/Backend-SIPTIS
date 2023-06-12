package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.StudentEditPersonalInfoDTO;
import backend.siptis.model.pjo.dto.TeacherEditPersonalInfoDTO;

public interface UserEditInformation {

    ServiceAnswer studentEditPersonalInfo(Long id, StudentEditPersonalInfoDTO dto);

    ServiceAnswer teacherEditPersonalInfo(Long id, TeacherEditPersonalInfoDTO dto);


}
