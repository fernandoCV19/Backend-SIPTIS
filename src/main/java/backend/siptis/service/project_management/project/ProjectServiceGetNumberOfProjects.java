package backend.siptis.service.project_management.project;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.project_management.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectServiceGetNumberOfProjects {

    private final ProjectRepository projectRepository;

    public ServiceAnswer getNumberOfProjectsByModalityAndCareer(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsByModalityAndCareer(id)).build();
    }

    public ServiceAnswer getNumberOfProjectsByAreaAndCareer(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsByAreasAndCareer(id)).build();
    }

    public ServiceAnswer getNumberOfProjectsBySubAreaAndCareer(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsBySubAreasAndCareer(id)).build();
    }

    public ServiceAnswer getNumberProjectsByPeriodAndCareer(Long careerId) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberProjectsByPeriodAndCareer(careerId)).build();
    }

    public ServiceAnswer getNumberProjectsByCareer(Long careerId) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getProjectsByCareer(careerId)).build();
    }
}
