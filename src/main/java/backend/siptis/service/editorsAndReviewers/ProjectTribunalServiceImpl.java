package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.Phase;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToTribunalHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        ProjectTribunal query = projectTribunalRepository.findByTribunalIdAndProjectId(idTribunal, idProject);
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
        ProjectTribunal query = projectTribunalRepository.findByTribunalIdAndProjectId(idTribunal, idProject);
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
