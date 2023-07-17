package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;

public interface AreasService {

    ServiceAnswer getAllUserAreas();

    ServiceAnswer getUserAreaById(int id);
}
