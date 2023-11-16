package backend.siptis.service.editorsAndReviewers.projectTribunalServices;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProjectTribunalServiceAcceptProject {

    private final ProjectTribunalRepository projectTribunalRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;

    public ServiceAnswer acceptProject(Long idTribunal, Long idProject) {
        if (siptisUserRepository.findById(idTribunal).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        if (projectRepository.findById(idProject).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectTribunal query = projectTribunalRepository.findByProject_IdAndTribunal_Id(idProject, idTribunal);
        if (query == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        if (Boolean.TRUE.equals(query.getAccepted())) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED).data(null).build();
        }

        query.setAccepted(Boolean.TRUE);
        projectTribunalRepository.save(query);
        return verifyChangeOfFase(query);
    }

    private ServiceAnswer verifyChangeOfFase(ProjectTribunal query) {
        Project project = query.getProject();
        boolean allTribunalsHaveAccepted = project.getTribunals().stream().allMatch(ProjectTribunal::getAccepted);

        if (allTribunalsHaveAccepted) {
            project.setPhase(PhaseName.ASSIGN_DEFENSE_PHASE.toString());
            projectRepository.save(project);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS CHANGED TO THE PHASE OF DEFENSE").build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS NOT CHANGED TO THE PHASE OF DEFENSE").build();
    }
}
