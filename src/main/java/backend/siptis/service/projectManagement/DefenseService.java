package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;
import backend.siptis.model.pjo.dto.projectManagement.ParametersOfReservationsDTO;

public interface DefenseService {

    public ServiceAnswer getPlaceReservationsAndDirectorByMonth(Integer month);

    public ServiceAnswer registerDefense(DefenseDTO newDefense);

    public ServiceAnswer removeDefense(Long projectId);
}
