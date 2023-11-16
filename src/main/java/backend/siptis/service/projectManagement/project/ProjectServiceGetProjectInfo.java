package backend.siptis.service.projectManagement.project;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.presentations.Presentation;
import backend.siptis.model.entity.presentations.Review;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.projectManagement.ProjectInformationDTO;
import backend.siptis.model.pjo.vo.projectManagement.ProjectCompleteInfoVO;
import backend.siptis.model.pjo.vo.projectManagement.ProjectInfoToAssignTribunalsVO;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToReviewSectionVO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.presentations.PresentationRepository;
import backend.siptis.model.repository.presentations.ReviewRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectServiceGetProjectInfo {

    private final ProjectRepository projectRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final PresentationRepository presentationRepository;
    private final ReviewRepository reviewRepository;

    public ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer) {
        Optional<Project> query = projectRepository.findById(idProject);
        if (query.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        if (siptisUserRepository.findById(idReviewer).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        Project project = query.get();

        List<Long> reviewerIds = projectRepository.getIdsListFromReviewers(idProject);
        if (!reviewerIds.contains(idReviewer)) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        Presentation presentation = presentationRepository.findTopByProjectIdOrderByDateDesc(idProject);
        if (presentation == null) {
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, -1, Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.THERE_IS_NO_PRESENTATION_YET).data(data).build();
        }
        if (Boolean.FALSE.equals(presentation.getReviewed())) {
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.TRUE, getDaysDifference(presentation.getDate()), Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
        }
        Review lastReview = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(idProject, idReviewer);
        if (lastReview == null) {
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, getDaysDifference(presentation.getDate()), Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
        }
        Integer numberOfDays = getDaysDifference(lastReview.getDate());
        ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, numberOfDays, Boolean.TRUE);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    public ServiceAnswer getProjectInfoToAssignTribunals(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
        if (query.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new ProjectInfoToAssignTribunalsVO(project)).build();
    }

    public ServiceAnswer getProjectInfo(Long id) {
        if (!projectRepository.existsById(id))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).build();

        Project project = projectRepository.findById(id).get();
        ProjectInformationDTO dto = new ProjectInformationDTO(project);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(dto).build();
    }

    public ServiceAnswer getAllProjectInfo(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
        if (query.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new ProjectCompleteInfoVO(project)).build();
    }

    public ServiceAnswer getProjectById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(optionalProject.get()).build();
    }

    private Integer getDaysDifference(LocalDateTime compare) {
        LocalDateTime now = LocalDateTime.now();
        return now.getDayOfYear() - compare.getDayOfYear();
    }
}
