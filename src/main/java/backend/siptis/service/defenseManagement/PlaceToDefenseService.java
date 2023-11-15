package backend.siptis.service.defenseManagement;

import backend.siptis.commons.ServiceAnswer;

public interface PlaceToDefenseService {

    ServiceAnswer getReservedDates();

    ServiceAnswer getAvailablePlaces();
}