package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.Phase;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTutorRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTutorServiceImpl implements ProjectTutorService {

    private final ProjectTutorRepository projectTutorRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedByTutorId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTutorId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedByTutorId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTutor> listaProyectos = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(id);
        return getProjects(listaProyectos);
    }

    @Override
    public ServiceAnswer acceptProject(Long idTutor, Long idProject) {
        if (siptisUserRepository.findById(idTutor).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        if (projectRepository.findById(idProject).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectTutor query = projectTutorRepository.findByTutorIdAndProjectId(idTutor, idProject);
        if (query == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        if (Boolean.TRUE.equals(query.getAccepted())) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED).data(null).build();
        }

        query.setAccepted(Boolean.TRUE);
        projectTutorRepository.save(query);
        return verifyChangeOfFase(query);
    }

    @Override
    public ServiceAnswer removeAcceptProject(Long idTutor, Long idProject) {
        if (siptisUserRepository.findById(idTutor).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        if (projectRepository.findById(idProject).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectTutor query = projectTutorRepository.findByTutorIdAndProjectId(idTutor, idProject);
        if (query == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        if (Boolean.FALSE.equals(query.getAccepted())) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_IS_ALREADY_NOT_ACCEPTED).data(null).build();
        }

        Project project = query.getProject();

        if (!project.getPhase().equals(Phase.REVIEWERS_PHASE.toString())) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_IS_ON_ANOTHER_PHASE).data(null).build();
        }

        query.setAccepted(Boolean.FALSE);
        projectTutorRepository.save(query);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS CHANGED THE ACCEPTED PARAMETER").build();
    }

    private ServiceAnswer verifyChangeOfFase(ProjectTutor query) {
        Project project = query.getProject();
        boolean allReviewersHaveAccepted = project.getSupervisors().stream().allMatch(ProjectSupervisor::getAccepted) &&
                project.getTeachers().stream().allMatch(ProjectTeacher::getAccepted) && project.getTutors().stream().allMatch(ProjectTutor::getAccepted);

        if (allReviewersHaveAccepted) {
            project.setPhase(Phase.TRIBUNALS_PHASE.toString());
            projectRepository.save(project);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS CHANGED TO THE PHASE OF TRIBUNALS").build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("THE PROJECT HAS NOT CHANGED TO THE PHASE OF TRIBUNALS").build();
    }

    private ServiceAnswer getProjects(List<ProjectTutor> listaProyectos) {
        if (listaProyectos.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(listaProyectos).build();
        }

        List<ProjectToHomePageVO> data = listaProyectos
                .stream()
                .map(aux -> new ProjectToHomePageVO(aux.getProject(), aux.getAccepted(), aux.getReviewed()))
                .collect(Collectors.toList());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}