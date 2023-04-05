package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.pjo.dto.projectManagement.ProjectToHomePageDTO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectSupervisorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectSupervisorServiceImpl implements ProjectSupervisorService {

    private final ProjectSupervisorRepository projectSupervisorRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedBySupervisorId(Long id) {
        if(projectSupervisorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectSupervisor> listaProyectos = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedBySupervisorId(Long id) {
        if(projectSupervisorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectSupervisor> listaProyectos = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedBySupervisorId(Long id) {
        if(projectSupervisorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectSupervisor> listaProyectos = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(id);
        return getProjects(listaProyectos);
    }

    private ServiceAnswer getProjects(List<ProjectSupervisor> listaProyectos){
        if(listaProyectos.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(listaProyectos).build();
        }

        List<ProjectToHomePageDTO> data = listaProyectos
                .stream()
                .map(aux -> new ProjectToHomePageDTO(aux.getProject(), aux.getAccepted(), aux.getReviewed()))
                .collect(Collectors.toList());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
