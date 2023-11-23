package backend.siptis.service.user_data;

import backend.siptis.commons.ServiceAnswer;

public interface UserCareerService {

    ServiceAnswer getCareerByName(String name);
}
