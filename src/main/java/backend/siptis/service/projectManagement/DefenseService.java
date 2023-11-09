package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;

public interface DefenseService {

    public ServiceAnswer getDefenseByMonth(Integer month);

    public ServiceAnswer registerDefense(DefenseDTO newDefense);

    public ServiceAnswer removeDefense(Long projectId);
}
