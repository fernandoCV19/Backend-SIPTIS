package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.editorsAndReviewers.ModalityRepository;
import backend.siptis.service.userData.SiptisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModalityServiceImpl implements ModalityService {

    @Autowired
    private ModalityRepository modalityRepository;
    private SiptisUserService siptisUserService;
    private ProjectService projectService;

    @Override
    public ServiceAnswer getAllModalities() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(modalityRepository.findAll()).build();
    }

    @Override
    public ServiceAnswer getModalityByUser(Long idUser) {
        Long projectId = siptisUserService.getProjectById(idUser);
        ServiceAnswer projectAnswer = projectService.getProjectById(projectId);
        if (projectAnswer.getServiceMessage() == ServiceMessage.OK) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(modalityRepository.findById(projectId)).build();
        } else {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).build();
        }
    }
}
