package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.pjo.dto.projectManagement.ProjectToHomePageDTO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTutorServiceImpl implements ProjectTutorService {

    private final ProjectTutorRepository projectTutorRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedByTutorId(Long id) {
        if(projectTutorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTutorId(Long id) {
        if(projectTutorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedByTutorId(Long id) {
        if(projectTutorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(id);
        return getProjects(listaProyectos);
    }

    private ServiceAnswer getProjects(List<ProjectTutor> listaProyectos){
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