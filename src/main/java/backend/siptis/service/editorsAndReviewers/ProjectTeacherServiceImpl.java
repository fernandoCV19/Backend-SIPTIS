package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import backend.siptis.model.pjo.dto.projectManagement.ProjectToHomePageDTO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTeacherServiceImpl implements ProjectTeacherService {

    private final ProjectTeacherRepository projectTeacherRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedByTeacherId(Long id) {
        if(projectTeacherRepository.findById(id).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTeacher> listaProyectos = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(id);
        return getProjects(listaProyectos);
    }


    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTeacherId(Long id) {
        if(projectTeacherRepository.findById(id).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTeacher> listaProyectos = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedByTeacherId(Long id) {
        if(projectTeacherRepository.findById(id).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTeacher> listaProyectos = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(id);
        return getProjects(listaProyectos);
    }

    private ServiceAnswer getProjects(List<ProjectTeacher> listaProyectos){
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
