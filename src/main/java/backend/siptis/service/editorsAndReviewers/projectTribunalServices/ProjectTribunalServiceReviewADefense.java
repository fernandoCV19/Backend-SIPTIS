package backend.siptis.service.editorsAndReviewers.projectTribunalServices;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.defenseManagement.Defense;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.editorsAndReviewers.ReviewADefenseDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectTribunalServiceReviewADefense {

    private final ProjectTribunalRepository projectTribunalRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;


    public ServiceAnswer reviewADefense(ReviewADefenseDTO reviewADefenseDTO) {
        Long projectID = reviewADefenseDTO.getProject();
        Optional<Project> projectOptional = projectRepository.findById(projectID);
        if (projectOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        Long tribunalId = reviewADefenseDTO.getTribunal();
        if (siptisUserRepository.findById(tribunalId).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectTribunal query = projectTribunalRepository.findByProject_IdAndTribunal_Id(projectID, tribunalId);
        if (query == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_TRIBUNAL_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }
        Double points = reviewADefenseDTO.getPoints();
        if (points < 0 || points > 100) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.SCORE_IS_NOT_VALID).data(null).build();
        }
        query.setDefensePoints(points);
        projectTribunalRepository.save(query);
        Project project = projectOptional.get();
        Defense defense = project.getDefense();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime defenseStart = defense.getDate().atTime(defense.getStartTime());
        if (now.isBefore(defenseStart)) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.DEFENSE_HAS_NOT_STARTED).data(null).build();
        }

        double newProjectDefensePoint = 0.0;
        int count = 0;
        for (ProjectTribunal aux : project.getTribunals()) {
            if (aux.getDefensePoints() != null) {
                newProjectDefensePoint += aux.getDefensePoints();
                count++;
            }
        }
        if (count > 2) {
            project.setPhase(PhaseName.POST_DEFENSE_PHASE.toString());
        }
        if(count != 0) {
            newProjectDefensePoint = newProjectDefensePoint / count;
        }
        project.setTotalDefensePoints(newProjectDefensePoint);
        projectRepository.save(project);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("SCORE HAS BEEN ASSIGNED").build();
    }
}
