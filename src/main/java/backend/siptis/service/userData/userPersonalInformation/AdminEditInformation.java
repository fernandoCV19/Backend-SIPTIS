package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.EditTeacherInformationDTO;

public interface AdminEditInformation {

    ServiceAnswer editStudentInformation(Long userID, EditStudentInformationDTO editDTO);

    ServiceAnswer editTeacherInformation(Long userID, EditTeacherInformationDTO editDTO);


}
