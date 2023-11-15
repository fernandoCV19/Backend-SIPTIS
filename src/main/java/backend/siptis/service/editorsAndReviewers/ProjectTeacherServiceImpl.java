package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTeacherRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTeacherServiceImpl implements ProjectTeacherService {

    private final ProjectTeacherRepository projectTeacherRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ServiceAnswer getAllProjectsNotAcceptedReviewedByTeacherId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTeacher> projectsList = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(id);
        return getProjects(projectsList);
    }


    @Override
    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTeacherId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }
        List<ProjectTeacher> projectsList = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(id);
        return getProjects(projectsList);
    }

    @Override
    public ServiceAnswer getAllProjectsAcceptedByTeacherId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTeacher> projectsList = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(id);
        return getProjects(projectsList);
    }

    @Override
    public ServiceAnswer acceptProject(Long idTeacher, Long idProject) {
        if (siptisUserRepository.findById(idTeacher).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        if (projectRepository.findById(idProject).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        ProjectTeacher query = projectTeacherRepository.findByTeacherIdAndProjectId(idTeacher, idProject);
        if (query == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        if (Boolean.TRUE.equals(query.getAccepted())) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED).data(null).build();
        }

        query.setAccepted(Boolean.TRUE);
        projectTeacherRepository.save(query);
        return verifyChangeOfFase(query);
    }

    private ServiceAnswer verifyChangeOfFase(ProjectTeacher query) {
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

    private ServiceAnswer getProjects(List<ProjectTeacher> listaProyectos) {
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
