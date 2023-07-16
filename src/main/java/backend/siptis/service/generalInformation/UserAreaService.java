package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;

public interface UserAreaService {

    ServiceAnswer getAllUserAreas();

    ServiceAnswer getUserAreaById(int id);
}
