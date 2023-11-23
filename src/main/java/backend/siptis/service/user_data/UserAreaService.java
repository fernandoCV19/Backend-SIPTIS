package backend.siptis.service.user_data;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.user_data.UserArea;
import backend.siptis.model.pjo.dto.general_information.user_area.CreateAreaDTO;

public interface UserAreaService {

    ServiceAnswer getAllUserAreas();

    ServiceAnswer createUserArea(CreateAreaDTO dto);

    ServiceAnswer deleteUserArea(Long id);

    UserArea getUserAreaById(int id);

    boolean userAreaExistById(int id);
}
