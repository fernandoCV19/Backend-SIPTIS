package backend.siptis.service.project_management;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.general_information.user_area.CreateAreaDTO;

public interface AreaService {

    ServiceAnswer getAllAreas();

    ServiceAnswer createArea(CreateAreaDTO dto);

    ServiceAnswer deleteArea(Long id);

}
