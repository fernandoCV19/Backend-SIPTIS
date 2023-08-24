package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;

public interface UserCareerService {


    ServiceAnswer getAllCareers();

    ServiceAnswer getCareerById(int id);

    ServiceAnswer getCareerByName(String name);
}
