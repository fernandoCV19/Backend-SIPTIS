package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateUserAreaDTO;

public interface UserAreaService {

    ServiceAnswer getAllUserAreas();

    ServiceAnswer createUserArea(CreateUserAreaDTO dto);

    ServiceAnswer deleteUserArea(Long id);

    UserArea getUserAreaById(int id);

    boolean userAreaExistById(int id);
}
