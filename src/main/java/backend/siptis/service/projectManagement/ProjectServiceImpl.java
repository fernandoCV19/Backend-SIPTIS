package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.projectManagement.Review;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToReviewSectionVO;
import backend.siptis.model.repository.projectManagement.PresentationRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.projectManagement.ReviewRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final PresentationRepository presentationRepository;
    private final ReviewRepository reviewRepository;
    private final SiptisUserRepository siptisUserRepository;

    @Override
    public ServiceAnswer obtenerProyectos(){
        List<Project> proyectos = projectRepository.findAll();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(proyectos).build();
    }

    @Override
    public ServiceAnswer obtenerPresentaciones (Long idProyecto){
        Optional<Project> oProyectoGrado = projectRepository.findById(idProyecto);
        if(oProyectoGrado.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project proyecto = oProyectoGrado.get();
        List<Presentation> presentaciones = proyecto.getPresentations().stream().toList();
        if (presentaciones.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.SIN_PRESENTACIONES).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(presentaciones).build();
    }

    @Override
    public ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer) {
        Optional<Project> query = projectRepository.findById(idProject);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_PROJECT_DOES_NOT_EXIST).data(null).build();
        }
        if(siptisUserRepository.findById(idReviewer).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_USER_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        Presentation presentation = presentationRepository.findTopByProjectIdOrderByDateDesc(idProject);
        Boolean studentChanges = presentation != null && !presentation.getReviewed();
        Integer numberOfDays = 0;

        if(Boolean.TRUE.equals(studentChanges)){
            numberOfDays =  getDaysDifference(presentation.getDate());
        }else{
            Review lastReview = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(idProject, idReviewer);
            if(lastReview == null){
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
            }
            numberOfDays = getDaysDifference(lastReview.getDate());
        }

        ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, studentChanges, numberOfDays);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    private Integer getDaysDifference(Date compare){
        Date now = new Date();

        // Diferencia en milisegundos
        long diffMillis = compare.getTime() - now.getTime();

        // Diferencia en días
        long diffDias = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);

        return (int) diffDias;
    }
}
