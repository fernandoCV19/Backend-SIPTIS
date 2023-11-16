package backend.siptis.service.presentations;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.presentations.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.InfoToReviewAProjectVO;
import backend.siptis.model.pjo.vo.projectManagement.ReviewShortInfoVO;
import backend.siptis.model.repository.presentations.PresentationRepository;
import backend.siptis.model.repository.presentations.ReviewRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.service.cloud.CloudManagementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PresentationServiceImpl implements PresentationService {

    private final CloudManagementService cloudManagementService;
    private final PresentationRepository presentationRepository;
    private final ProjectRepository projectRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ServiceAnswer createPresentation(Long projectId, MultipartFile bluebookFile, MultipartFile projectFile) {
        Optional<Presentation> pendingPresentation = presentationRepository.findByProjectIdAndReviewed(projectId, false);
        if (pendingPresentation.isPresent()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PENDING_PRESENTATION).data(null).build();
        }
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project project = optionalProject.get();
        Presentation presentation = new Presentation();
        if (bluebookFile != null) {
            String key = cloudManagementService.putObject(bluebookFile, "Libro-Azul/");
            project.setBlueBookPath(key);
            presentation.setBlueBookPath(key);
        }
        if (projectFile != null) {
            String key = cloudManagementService.putObject(projectFile, "Trabajos-Grado/");
            project.setProjectPath(key);
            presentation.setProjectPath(key);
        }
        presentation.setDate(LocalDateTime.now());
        presentation.setPhase(project.getPhase());
        presentation.setProject(project);
        presentationRepository.save(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTATION_CREATED).data(presentation).build();

    }

    @Override
    public ServiceAnswer getReviewsFromAPresentation(Long idPresentation) {
        Optional<Presentation> optionalPresentation = presentationRepository.findById(idPresentation);
        if (optionalPresentation.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Presentation presentation = optionalPresentation.get();

        List<Map<String, Object>> reviews = presentation
                .getReviews()
                .stream()
                .map(review -> {
                    Map<String, Object> jsonMap = new HashMap<>();
                    jsonMap.put("id", review.getId());
                    jsonMap.put("date", review.getDate());
                    jsonMap.put("documentPath", review.getDocumentPath());
                    jsonMap.put("commentary", review.getCommentary());
                    Map<String, Object> reviewerInfo = new HashMap<>();
                    reviewerInfo.put("id", review.getSiptisUser().getId());
                    reviewerInfo.put("names", review.getSiptisUser().getUserInformation().getNames());
                    reviewerInfo.put("lastNames", review.getSiptisUser().getUserInformation().getLastNames());

                    jsonMap.put("reviewer", reviewerInfo);
                    return jsonMap;
                })
                .toList();

        if (reviews.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_REVIEWS).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(reviews).build();
    }

    @Override
    public ServiceAnswer deletePresentation(Long presentationId) {
        Optional<Presentation> optionalPresentation = presentationRepository.findById(presentationId);
        if (optionalPresentation.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Presentation presentation = optionalPresentation.get();
        Project toProject = presentation.getProject();
        String blue = presentation.getBlueBookPath();
        String project = presentation.getProjectPath();
        if (blue != null)
            cloudManagementService.deleteObject(blue);
        if (project != null)
            cloudManagementService.deleteObject(project);
        presentation.getReviews().stream()
                .peek(review -> {
                    review.setPresentation(null);
                    reviewRepository.delete(review);
                });

        presentationRepository.delete(presentation);
        Presentation backup = presentationRepository.findTopByProjectIdOrderByDateDesc(toProject.getId());
        if (backup == null) {
            toProject.setBlueBookPath(null);
            toProject.setProjectPath(null);
        } else {
            toProject.setBlueBookPath(backup.getBlueBookPath());
            toProject.setProjectPath(backup.getProjectPath());
        }
        projectRepository.save(toProject);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTATION_DELETED).data(null).build();
    }


    @Override
    public ServiceAnswer getReviewsFromLastPresentation(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        Presentation presentation = presentationRepository.findTopByProjectIdOrderByDateDesc(projectId);
        if (presentation == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.THERE_IS_NO_PRESENTATION_YET).data(null).build();
        }
        List<ReviewShortInfoVO> reviews = presentation.getReviews().stream().map(ReviewShortInfoVO::new).toList();
        InfoToReviewAProjectVO data = new InfoToReviewAProjectVO(projectOptional.get(), reviews);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
