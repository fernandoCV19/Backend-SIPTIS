package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;

public interface UserAreaService {

    ServiceAnswer getAllUserAreas();

    ServiceAnswer createUserArea(CreateAreaDTO dto);

    ServiceAnswer deleteUserArea(Long id);

    UserArea getUserAreaById(int id);

    boolean userAreaExistById(int id);
}
