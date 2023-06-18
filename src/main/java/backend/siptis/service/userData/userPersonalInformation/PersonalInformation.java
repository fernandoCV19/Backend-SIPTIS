package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.commons.ServiceAnswer;

public interface PersonalInformation {

    ServiceAnswer getPersonalInformation();

    ServiceAnswer getPersonalInformationById();
}
