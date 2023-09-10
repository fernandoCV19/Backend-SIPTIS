package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;

public interface DefenseService {

    public ServiceAnswer getPlaceByMonth(Long placeId, Integer month);

}
