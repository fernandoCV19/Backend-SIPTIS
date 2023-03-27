package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.editoresYRevisores.ProjectTutor;
import backend.siptis.model.pjo.dto.gestionProyecto.ProjectToHomePageDTO;
import backend.siptis.model.repository.editoresYRevisores.ProjectTutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTutorServiceImpl implements ProjectTutorService {

    private final ProjectTutorRepository projectTutorRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedByIdTutor(Long id) {
        if(projectTutorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorAndAcceptedIsFalseAndReviewedIsTrue(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByIdTutor(Long id) {
        if(projectTutorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorAndAcceptedIsFalseAndReviewedIsFalse(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedByIdTutor(Long id) {
        if(projectTutorRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorAndAcceptedIsTrue(id);
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