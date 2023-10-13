package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.editorsAndReviewers.ModalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModalityServiceImpl implements ModalityService {

    @Autowired
    private ModalityRepository modalityRepository;

    @Override
    public ServiceAnswer getAllModalities() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(modalityRepository.findAll()).build();
    }
}
