package backend.siptis.service.userData.GeneralInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;

public interface GeneralInformationService {

    //ServiceAnswer getAllCareers();

    //ServiceAnswer getAllUserAreas();

    //ServiceAnswer getAllProjectAreas();

    //ServiceAnswer getAreaById(int id);

    //ServiceAnswer getCareerById(int id);

    ServiceAnswer getAllPotentialTribunals();

    ServiceAnswer getProjectsByAreas(UserSelectedAreasDTO dto);

    ServiceAnswer getPotentialTribunalsByAreas(UserSelectedAreasDTO dto);
}
