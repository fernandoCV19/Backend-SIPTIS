package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;

public interface PlaceToDefenseService {

    ServiceAnswer getReservedDates();

    ServiceAnswer getAvailablePlaces();
}