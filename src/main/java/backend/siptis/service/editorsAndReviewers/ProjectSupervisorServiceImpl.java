package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToHomePageVO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editorsAndReviewers.ProjectSupervisorRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectSupervisorServiceImpl implements ProjectSupervisorService {

    private final ProjectSupervisorRepository projectSupervisorRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedBySupervisorId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectSupervisor> projectsList = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(id);
        return getProjects(projectsList);
    }

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedBySupervisorId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectSupervisor> projectsList = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(id);
        return getProjects(projectsList);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedBySupervisorId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectSupervisor> projectsList = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(id);
        return getProjects(projectsList);
    }

    @Override
    public ServiceAnswer acceptProject(Long idSupervisor, Long idProject) {
        if (siptisUserRepository.findById(idSupervisor).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        if (projectRepository.findById(idProject).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectSupervisor query = projectSupervisorRepository.findBySupervisorIdAndProjectId(idSupervisor, idProject);
        if (query == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        if (Boolean.TRUE.equals(query.getAccepted())) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED).data(null).build();
        }

        query.setAccepted(Boolean.TRUE);
        projectSupervisorRepository.save(query);
        return verifyChangeOfPhase(query);
    }

    private ServiceAnswer verifyChangeOfPhase(ProjectSupervisor query) {
        Project project = query.getProject();
        boolean allReviewersHaveAccepted = project.getSupervisors().stream().allMatch(ProjectSupervisor::getAccepted) &&
                project.getTeachers().stream().allMatch(ProjectTeacher::getAccepted) && project.getTutors().stream().allMatch(ProjectTutor::getAccepted);

        if (allReviewersHaveAccepted) {
            project.setPhase(PhaseName.ASSIGN_TRIBUNALS_PHASE.toString());
            projectRepository.save(project);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS CHANGED TO THE PHASE OF TRIBUNALS").build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS NOT CHANGED TO THE PHASE OF TRIBUNALS").build();
    }

    private ServiceAnswer getProjects(List<ProjectSupervisor> projectsList) {
        if (projectsList.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(projectsList).build();
        }

        List<ProjectToHomePageVO> data = projectsList
                .stream()
                .map(aux -> new ProjectToHomePageVO(aux.getProject(), aux.getAccepted(), aux.getReviewed()))
                .collect(Collectors.toList());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
