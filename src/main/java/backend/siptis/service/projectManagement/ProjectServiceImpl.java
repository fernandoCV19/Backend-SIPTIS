package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.projectManagement.Review;
import backend.siptis.model.pjo.vo.projectManagement.ProjectCompleteInfo;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToReviewSectionVO;
import backend.siptis.model.repository.projectManagement.PresentationRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.projectManagement.ReviewRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final PresentationRepository presentationRepository;
    private final ReviewRepository reviewRepository;
    private final SiptisUserRepository siptisUserRepository;

    @Override
    public ServiceAnswer getProjects(){
        List<Project> proyectos = projectRepository.findAll();

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(proyectos).build();
    }

    @Override
    public ServiceAnswer getPresentations (Long idProyecto){
        Optional<Project> oProyectoGrado = projectRepository.findById(idProyecto);
        if(oProyectoGrado.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project proyecto = oProyectoGrado.get();
        List<Presentation> presentaciones = proyecto.getPresentations().stream().toList();
        if (presentaciones.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PRESENTATIONS).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(presentaciones).build();
    }

    @Override
    public ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer) {
        Optional<Project> query = projectRepository.findById(idProject);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        if(siptisUserRepository.findById(idReviewer).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        Project project = query.get();

        List<Long> reviewerIds = projectRepository.getIdsListFromReviewers(idProject);
        if(!reviewerIds.contains(idReviewer)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        Presentation presentation = presentationRepository.findTopByProjectIdOrderByDateDesc(idProject);
        if(presentation == null){
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, -1, Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.THERE_IS_NO_PRESENTATION_YET).data(data).build();
        }

        if(Boolean.FALSE.equals(presentation.getReviewed())){
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.TRUE, getDaysDifference(presentation.getDate()), Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
        }

        Review lastReview = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(idProject, idReviewer);

        if(lastReview == null){
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, getDaysDifference(presentation.getDate()), Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
        }

        Integer numberOfDays = getDaysDifference(lastReview.getDate());
        ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, numberOfDays, Boolean.TRUE);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    @Override
    public ServiceAnswer getAllProjectInfo(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new ProjectCompleteInfo(project)).build();
    }

    private Integer getDaysDifference(Date compare){
        Date now = new Date();

        // Diferencia en milisegundos
        long diffMillis = now.getTime() - compare.getTime() ;

        // Diferencia en días
        long diffDias = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);

        return (int) diffDias;
    }
}
