package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;

public interface SubAreaService {
    ServiceAnswer getAllSubAreas();

    ServiceAnswer createSubArea(CreateAreaDTO dto);

    ServiceAnswer deleteSubArea(Long id);
}
