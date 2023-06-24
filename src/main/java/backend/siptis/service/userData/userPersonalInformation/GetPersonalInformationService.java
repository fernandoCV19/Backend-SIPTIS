package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.commons.ServiceAnswer;

public interface GetPersonalInformationService {

    ServiceAnswer getPersonalInformationById(Long id);

    ServiceAnswer getStudentCareerById(Long id);

    ServiceAnswer getTeacherAreasById(Long id);
}
