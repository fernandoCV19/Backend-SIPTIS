package backend.siptis.service.editors_and_reviewers.project_tribunal_services;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
import backend.siptis.model.pjo.vo.project_management.ProjectToHomePageVO;
import backend.siptis.model.pjo.vo.project_management.ProjectToTribunalHomePageVO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTribunalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectTribunalServiceGetProjects {

    private final ProjectTribunalRepository projectTribunalRepository;
    private final SiptisUserRepository siptisUserRepository;

    public ServiceAnswer getAllProjectsNotAcceptedReviewedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(id);

        return getProjects(projectsList);
    }

    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(id);

        return getProjects(projectsList);
    }

    public ServiceAnswer getAllProjectsAcceptedWithoutDefensePointsByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(id);
        List<ProjectToHomePageVO> data = projectsList
                .stream()
                .map(aux -> new ProjectToHomePageVO(aux.getProject(), aux.getAccepted(), aux.getReviewed()))
                .toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    public ServiceAnswer getAllProjectsDefendedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(id);
        List<ProjectToHomePageVO> data = projectsList
                .stream()
                .map(aux -> new ProjectToHomePageVO(aux.getProject(), aux.getAccepted(), aux.getReviewed()))
                .toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    private ServiceAnswer getProjects(List<ProjectTribunal> projectsList) {
        if (projectsList.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(projectsList).build();
        }

        List<ProjectToTribunalHomePageVO> data = projectsList
                .stream()
                .filter(projectTribunal -> projectTribunal.getProject().getPhase().equals(PhaseName.TRIBUNALS_PHASE.toString()))
                .map(aux -> new ProjectToTribunalHomePageVO(aux.getProject(), aux.getDefensePoints(), aux.getAccepted(), aux.getReviewed()))
                .toList();

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
