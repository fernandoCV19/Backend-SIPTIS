package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.Phase;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.Defense;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.editorsAndReviewers.ReviewADefenseDTO;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToTribunalHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public ServiceAnswer getAllProjectsNotReviewedByTribunalId(Long id) {
        if(siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> listaProyectos = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(id);

        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsReviewedNotAcceptedByTribunalId(Long id) {
        if(siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> listaProyectos = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(id);

        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedWithoutDefensePointsByTribunalId(Long id) {
        if(siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> listaProyectos = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(id);

        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsDefendedByTribunalId(Long id) {
        if(siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> listaProyectos = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(id);

        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer acceptProject(Long idTribunal, Long idProject) {
        if(siptisUserRepository.findById(idTribunal).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        if(projectRepository.findById(idProject).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectTribunal query = projectTribunalRepository.findByProject_IdAndTribunal_Id(idProject, idTribunal);
        if(query == null){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        if(Boolean.TRUE.equals(query.getAccepted())){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED).data(null).build();
        }

        query.setAccepted(Boolean.TRUE);
        projectTribunalRepository.save(query);
        return verifyChangeOfFase(query);
    }

    @Override
    public ServiceAnswer removeAcceptProject(Long idTribunal, Long idProject) {
        if(siptisUserRepository.findById(idTribunal).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        if(projectRepository.findById(idProject).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectTribunal query = projectTribunalRepository.findByProject_IdAndTribunal_Id(idProject, idTribunal);
        if(query == null){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        if(Boolean.FALSE.equals(query.getAccepted())){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_IS_ALREADY_NOT_ACCEPTED).data(null).build();
        }

        Project project = query.getProject();

        if(!project.getPhase().equals(Phase.TRIBUNALS_PHASE.toString())){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_IS_ON_ANOTHER_PHASE).data(null).build();
        }

        query.setAccepted(Boolean.FALSE);
        projectTribunalRepository.save(query);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS CHANGED THE ACCEPTED PARAMETER").build();
    }

    @Override
    public ServiceAnswer reviewADefense(ReviewADefenseDTO reviewADefenseDTO) {
        Long projectID = reviewADefenseDTO.getProject();
        Optional<Project> projectOptional = projectRepository.findById(projectID);
        if(projectOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        Long tribunalId = reviewADefenseDTO.getTribunal();
        if(siptisUserRepository.findById(tribunalId).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectTribunal query = projectTribunalRepository.findByProject_IdAndTribunal_Id(projectID, tribunalId);
        if(query == null){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_TRIBUNAL_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }
        Double points = reviewADefenseDTO.getPoints();
        if(points < 0 || points > 100){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.SCORE_IS_NOT_VALID).data(null).build();
        }
        query.setDefensePoints(points);
        projectTribunalRepository.save(query);
        Project project = projectOptional.get();
        Defense defense = project.getDefense();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime defenseStart = defense.getDate().atTime(defense.getStartTime());
        if(now.isBefore(defenseStart)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.DEFENSE_HAS_NOT_STARTED).data(null).build();
        }

        Double newProjectDefensePoint = 0.0;
        Integer count = 0;
        for (ProjectTribunal aux:project.getTribunals()) {
            if(aux.getDefensePoints() != null) {
                newProjectDefensePoint += aux.getDefensePoints();
                count++;
            }
        }
        if(count > 2){
            project.setPhase(Phase.POST_DEFENSE_PHASE.toString());
        }
        newProjectDefensePoint = newProjectDefensePoint / count;
        project.setTotalDefensePoints(newProjectDefensePoint);
        projectRepository.save(project);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("SCORE HAS BEEN ASSIGNED").build();
    }

    @Override
    public ServiceAnswer removeTribunals(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(optionalProject.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data("").build();
        }
        Project project = optionalProject.get();
        if(project.getTribunals().isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.TRIBUNALS_ERROR).data("PROJECT DOES NOT HAVE TRIBUNALS TO REMOVE").build();
        }

        for (ProjectTribunal tribunal: project.getTribunals()) {
            projectTribunalRepository.delete(tribunal);
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("TRIBUNALS HAS BEEN REMOVED").build();
    }

    private ServiceAnswer verifyChangeOfFase(ProjectTribunal query) {
        Project project = query.getProject();
        boolean allTribunalsHaveAccepted = project.getTribunals().stream().allMatch(ProjectTribunal::getAccepted);

        if(allTribunalsHaveAccepted){
            project.setPhase(Phase.DEFENSE_PHASE.toString());
            projectRepository.save(project);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS CHANGED TO THE PHASE OF DEFENSE").build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS NOT CHANGED TO THE PHASE OF DEFENSE").build();
    }

    private ServiceAnswer getProjects(List<ProjectTribunal> listaProyectos){
        if(listaProyectos.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(listaProyectos).build();
        }

        List<ProjectToTribunalHomePageVO> data = listaProyectos
                .stream()
                .map(aux -> new ProjectToTribunalHomePageVO(aux.getProject(), aux.getDefensePoints(), aux.getAccepted(), aux.getReviewed()))
                .collect(Collectors.toList());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
