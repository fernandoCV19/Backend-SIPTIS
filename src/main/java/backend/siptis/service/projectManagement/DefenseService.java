package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;

public interface DefenseService {

    ServiceAnswer getDefenseByMonth(Integer month);

    ServiceAnswer registerDefense(DefenseDTO newDefense);

    ServiceAnswer removeDefense(Long projectId);
}
