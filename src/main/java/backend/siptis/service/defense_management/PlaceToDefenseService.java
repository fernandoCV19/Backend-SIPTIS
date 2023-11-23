package backend.siptis.service.defense_management;

import backend.siptis.commons.ServiceAnswer;

public interface PlaceToDefenseService {

    ServiceAnswer getReservedDates();

    ServiceAnswer getAvailablePlaces();
}