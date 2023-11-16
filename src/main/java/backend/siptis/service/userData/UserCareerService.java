package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;

public interface UserCareerService {

    ServiceAnswer getCareerByName(String name);
}
