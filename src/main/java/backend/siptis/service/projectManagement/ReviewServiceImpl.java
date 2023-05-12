package backend.siptis.service.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Review;
import backend.siptis.model.repository.projectManagement.PresentationRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.projectManagement.ReviewRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final PresentationRepository presentationRepository;
    private final CloudManagementService cloudManagementService;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ServiceAnswer addReview(Long projectId, Long userId, MultipartFile multipartFile, String commentary) {
        Optional<Presentation> presentationOptional = presentationRepository.findById(projectId);
        if(presentationOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(userId);
        if(userOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        List<Long> reviewersIds = projectRepository.getIdsListFromReviewers(projectId);
        if(!reviewersIds.contains(userId)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }
        Presentation lastPresentation = presentationRepository.findTopByProjectIdOrderByDateDesc(projectId);
        if(lastPresentation == null){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.THERE_IS_NO_PRESENTATION_YET).data(null).build();
        }

        try{
            String newKey = cloudManagementService.putObject(multipartFile, "Reviews/");
            Review newReview = new Review(newKey, commentary, userOptional.get(), lastPresentation, new Date());
            Optional<Review> lastPossibleReview = lastPresentation.getReviews().stream().filter(review -> Objects.equals(review.getSiptisUser().getId(), userId)).findFirst();
            if(lastPossibleReview.isEmpty()){
                newReview.setId(lastPossibleReview.get().getId());
            }
            reviewRepository.save(newReview);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("REVIEW SAVED").build();
        }catch (Exception e){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.THERE_IS_A_PROBLEM_WITH_THE_CLOUD).data("REVIEW SAVED").build();
        }
    }
}
