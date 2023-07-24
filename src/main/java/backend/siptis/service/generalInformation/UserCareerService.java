package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;

public interface UserCareerService {


    ServiceAnswer getAllCareers();

    ServiceAnswer getCareerById(int id);

    ServiceAnswer getCareerByName(String name);
}
