package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;

public interface AreasService {

    ServiceAnswer getAllAreas();

    ServiceAnswer getAreaById(int id);

    ServiceAnswer getAllProjectAreas();
}
