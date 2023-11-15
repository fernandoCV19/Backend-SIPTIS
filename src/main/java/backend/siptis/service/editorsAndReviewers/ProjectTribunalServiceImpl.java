package backend.siptis.service.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.defenseManagement.Defense;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.editorsAndReviewers.ReviewADefenseDTO;
import backend.siptis.model.pjo.dto.projectManagement.AssignTribunalsDTO;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToTribunalHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTribunalServiceImpl implements ProjectTribunalService {

    private final ProjectTribunalRepository projectTribunalRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(id);

        return getProjects(projectsList);
    }

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(id);

        return getProjects(projectsList);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedWithoutDefensePointsByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(id);

        return getProjects(projectsList);
    }

    @Override
    public ServiceAnswer getAllProjectsDefendedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(id);

        return getProjects(projectsList);
    }

    @Override
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

    @Override
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

        Double newProjectDefensePoint = 0.0;
        Integer count = 0;
        for (ProjectTribunal aux : project.getTribunals()) {
            if (aux.getDefensePoints() != null) {
                newProjectDefensePoint += aux.getDefensePoints();
                count++;
            }
        }
        if (count > 2) {
            project.setPhase(PhaseName.POST_DEFENSE_PHASE.toString());
        }
        newProjectDefensePoint = newProjectDefensePoint / count;
        project.setTotalDefensePoints(newProjectDefensePoint);
        projectRepository.save(project);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("SCORE HAS BEEN ASSIGNED").build();
    }

    @Override
    public ServiceAnswer removeTribunals(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data("").build();
        }
        Project project = optionalProject.get();
        if (project.getTribunals().isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.TRIBUNALS_ERROR).data("PROJECT DOES NOT HAVE TRIBUNALS TO REMOVE").build();
        }

        if (project.getTotalDefensePoints() != null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.TRIBUNALS_ERROR).data("THIS PROJECT HAS BEEN DEFENDED").build();
        }

        for (ProjectTribunal tribunal : project.getTribunals()) {
            projectTribunalRepository.delete(tribunal);
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("TRIBUNALS HAS BEEN REMOVED").build();
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

    private ServiceAnswer getProjects(List<ProjectTribunal> listaProyectos) {
        if (listaProyectos.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(listaProyectos).build();
        }

        List<ProjectToTribunalHomePageVO> data = listaProyectos
                .stream()
                .map(aux -> new ProjectToTribunalHomePageVO(aux.getProject(), aux.getDefensePoints(), aux.getAccepted(), aux.getReviewed()))
                .collect(Collectors.toList());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    @Override
    public ServiceAnswer assignTribunals(AssignTribunalsDTO assignTribunalsDTO) {
        List<Long> tribunalsIds = assignTribunalsDTO.getTribunalsIds();
        Long projectId = assignTribunalsDTO.getProjectId();
        List<SiptisUser> tribunals = new ArrayList<>();
        for (Long id : tribunalsIds) {
            Optional<SiptisUser> user = siptisUserRepository.findById(id);
            if (user.isEmpty()) {
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(id).build();
            }
            if (user.get().getRoles().stream().noneMatch(role -> role.getName().equals("TRIBUNAL"))) {
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_IS_NOT_A_TRIBUNAL).data(id).build();
            }
            tribunals.add(user.get());
        }
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        for (SiptisUser user : tribunals) {
            ProjectTribunal projectTribunal = new ProjectTribunal(user, project.get());
            projectTribunalRepository.save(projectTribunal);
        }

        Project projectNotOptional = project.get();
        projectNotOptional.setPhase(PhaseName.PRE_DEFENSE_PHASE.toString());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("Tribunals has been assigned to the project").build();
    }
}
