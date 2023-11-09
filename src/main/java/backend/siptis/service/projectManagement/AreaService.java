package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;

public interface AreaService {

    ServiceAnswer getAllAreas();

    ServiceAnswer createArea(CreateAreaDTO dto);

    ServiceAnswer deleteArea(Long id);

}
