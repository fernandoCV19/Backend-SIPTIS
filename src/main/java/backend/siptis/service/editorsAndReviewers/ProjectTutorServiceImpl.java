package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTutorRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTutorServiceImpl implements ProjectTutorService {

    private final ProjectTutorRepository projectTutorRepository;
    private final SiptisUserRepository siptisUserRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedByTutorId(Long id) {
        if(siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTutorId(Long id) {
        if(siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedByTutorId(Long id) {
        if(siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(id);
        return getProjects(listaProyectos);
    }

    private ServiceAnswer getProjects(List<ProjectTutor> listaProyectos){
        if(listaProyectos.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(listaProyectos).build();
        }

        List<ProjectToHomePageVO> data = listaProyectos
                .stream()
                .map(aux -> new ProjectToHomePageVO(aux.getProject(), aux.getAccepted(), aux.getReviewed()))
                .collect(Collectors.toList());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}