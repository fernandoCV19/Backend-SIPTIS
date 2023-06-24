package backend.siptis.service.userData.GeneralInformation;

import backend.siptis.commons.ServiceAnswer;

public interface GeneralInformationService {

    ServiceAnswer getAllCareers();

    ServiceAnswer getAllUserAreas();

    ServiceAnswer getAreaById(int id);

    ServiceAnswer getCareerById(int id);
}
