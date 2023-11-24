package backend.siptis.service.project_management.project;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.user_data.UserCareer;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.model.repository.user_data.UserCareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectServiceGetNumberOfProjects {

    private final ProjectRepository projectRepository;
    private final UserCareerRepository userCareerRepository;

    public ServiceAnswer getNumberOfProjectsByModalityAndCareer(String name) {
        Optional<UserCareer> oUserCareer = userCareerRepository.findByName(name.toUpperCase());
        if(oUserCareer.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsByModalityAndCareer(oUserCareer.get().getId())).build();
    }

    public ServiceAnswer getNumberOfProjectsByAreaAndCareer(String name) {
        Optional<UserCareer> oUserCareer = userCareerRepository.findByName(name.toUpperCase());
        if(oUserCareer.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsByAreasAndCareer(oUserCareer.get().getId())).build();
    }

    public ServiceAnswer getNumberOfProjectsBySubAreaAndCareer(String name) {
        Optional<UserCareer> oUserCareer = userCareerRepository.findByName(name.toUpperCase());
        if(oUserCareer.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsBySubAreasAndCareer(oUserCareer.get().getId())).build();
    }

    public ServiceAnswer getNumberProjectsByPeriodAndCareer(String name) {
        Optional<UserCareer> oUserCareer = userCareerRepository.findByName(name.toUpperCase());
        if(oUserCareer.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberProjectsByPeriodAndCareer(oUserCareer.get().getId())).build();
    }

    public ServiceAnswer getNumberProjectsByCareer(String name) {
        Optional<UserCareer> oUserCareer = userCareerRepository.findByName(name.toUpperCase());
        if(oUserCareer.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getProjectsByCareer(oUserCareer.get().getId())).build();
    }
}
