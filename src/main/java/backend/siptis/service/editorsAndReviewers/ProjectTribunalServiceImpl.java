package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.pjo.dto.projectManagement.ProjectToTribunalHomePageDTO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTribunalServiceImpl implements ProjectTribunalService {

    private final ProjectTribunalRepository projectTribunalRepository;

    @Override
    public ServiceAnswer getAllProjectsNotReviewedByTribunalId(Long id) {
        if(projectTribunalRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> listaProyectos = projectTribunalRepository.findByTribunalAndAcceptedIsFalseAndReviewedIsFalse(id);

        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsReviewedNotAcceptedByTribunalId(Long id) {
        if(projectTribunalRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> listaProyectos = projectTribunalRepository.findByTribunalAndAcceptedIsFalseAndReviewedIsTrue(id);

        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedWithoutDefensePointsByTribunalId(Long id) {
        if(projectTribunalRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> listaProyectos = projectTribunalRepository.findByTribunalAndAcceptedIsTrueAndDefensePointsIsNull(id);

        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsDefendedByTribunalId(Long id) {
        if(projectTribunalRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> listaProyectos = projectTribunalRepository.findByTribunalAndDefensePointsIsNotNull(id);

        return getProjects(listaProyectos);
    }

    private ServiceAnswer getProjects(List<ProjectTribunal> listaProyectos){
        if(listaProyectos.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(listaProyectos).build();
        }

        List<ProjectToTribunalHomePageDTO> data = listaProyectos
                .stream()
                .map(aux -> new ProjectToTribunalHomePageDTO(aux.getProject(), aux.getDefensePoints(), aux.getAccepted(), aux.getReviewed()))
                .collect(Collectors.toList());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
