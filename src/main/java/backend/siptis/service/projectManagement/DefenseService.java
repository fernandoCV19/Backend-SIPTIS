package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.projectManagement.ParametersOfReservationsDTO;

public interface DefenseService {

    public ServiceAnswer getPlaceReservationsAndDirectorByMonth(Long placeId, Integer month, Long directorId);

}
