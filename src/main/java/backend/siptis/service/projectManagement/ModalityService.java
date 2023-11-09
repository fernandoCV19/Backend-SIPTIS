package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;

public interface ModalityService {

    ServiceAnswer getAllModalities();

    ServiceAnswer getModalityByUser(Long idUser);
}
