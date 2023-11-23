package backend.siptis.service.defense_management;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.project_management.DefenseDTO;

public interface DefenseService {

    ServiceAnswer getDefenseByMonth(Integer month);

    ServiceAnswer registerDefense(DefenseDTO newDefense);

    ServiceAnswer removeDefense(Long projectId);
}
