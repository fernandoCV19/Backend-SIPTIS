package backend.siptis.service.project_management.project;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.pjo.vo.project_management.InfoToDefensesSectionVO;
import backend.siptis.model.pjo.vo.project_management.ProjectCompleteInfoVO;
import backend.siptis.model.pjo.vo.project_management.ProjectsWithoutAndWithTribunalsVO;
import backend.siptis.model.pjo.vo.project_management.ProjectsWithoutAndWithoutDefensePlaceVO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.project_management.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectServiceGetProjects {

    private final ProjectRepository projectRepository;
    private final ProjectTribunalRepository projectTribunalRepository;
    private final SiptisUserRepository siptisUserRepository;

    public ServiceAnswer getProjects() {
        List<Project> projects = projectRepository.findAll();
        if (projects.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(projects).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projects).build();
    }

    public ServiceAnswer getProjectsList(String search, Pageable pageable) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(projectRepository.searchProject(search, pageable))
                .build();
    }

    public ServiceAnswer getProjectsToDefenseOrDefended(Long userId) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }

        List<Project> projectsToDefend = projectTribunalRepository
                .findByTribunal_IdAndProject_PhaseAndDefensePointsNull(userId, PhaseName.DEFENSE_PHASE.toString())
                .stream()
                .map(ProjectTribunal::getProject)
                .toList();

        List<Project> projectsDefended = projectTribunalRepository
                .findByTribunal_IdAndProject_PhaseAndDefensePointsNotNull(userId, PhaseName.POST_DEFENSE_PHASE.toString())
                .stream()
                .map(ProjectTribunal::getProject)
                .toList();

        InfoToDefensesSectionVO data = new InfoToDefensesSectionVO(projectsToDefend, projectsDefended);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    public ServiceAnswer getProjectsWithoutAndWithTribunals() {
        List<Project> projects = projectRepository.findAll();

        List<ProjectCompleteInfoVO> withTribunals = projects
                .stream()
                .filter(project -> !project.getTribunals().isEmpty() && project.getTotalDefensePoints() == null)
                .map(ProjectCompleteInfoVO::new).toList();

        List<ProjectCompleteInfoVO> withoutTribunals = projects
                .stream().filter(project -> project.getPhase().equals(PhaseName.ASSIGN_TRIBUNALS_PHASE.toString()))
                .map(ProjectCompleteInfoVO::new).toList();

        ProjectsWithoutAndWithTribunalsVO data = new ProjectsWithoutAndWithTribunalsVO(withTribunals, withoutTribunals);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    public ServiceAnswer getProjectsWithoutAndWithDefensePlace() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectCompleteInfoVO> withDefense = projects
                .stream()
                .filter(project -> project.getDefense() != null && project.getPhase().equals(PhaseName.DEFENSE_PHASE.toString()) && project.getTotalDefensePoints() == null)
                .map(ProjectCompleteInfoVO::new)
                .toList();
        List<ProjectCompleteInfoVO> withoutDefense = projects
                .stream()
                .filter(project -> project.getDefense() == null && project.getPhase().equals(PhaseName.DEFENSE_PHASE.toString()))
                .map(ProjectCompleteInfoVO::new)
                .toList();
        ProjectsWithoutAndWithoutDefensePlaceVO data = new ProjectsWithoutAndWithoutDefensePlaceVO(withDefense, withoutDefense);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
