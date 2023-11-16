package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.projectManagement.ModalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ModalityServiceImpl implements ModalityService {

    private final ModalityRepository modalityRepository;

    @Override
    public ServiceAnswer getAllModalities() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(modalityRepository.findAll()).build();
    }

}
