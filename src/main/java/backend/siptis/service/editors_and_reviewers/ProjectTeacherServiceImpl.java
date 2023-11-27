package backend.siptis.service.editors_and_reviewers;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectSupervisor;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTeacher;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTutor;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.pjo.vo.project_management.ProjectToHomePageVO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTeacherRepository;
import backend.siptis.model.repository.project_management.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<ProjectToHomePageVO> data = projectsList
                .stream()
                .map(aux -> new ProjectToHomePageVO(aux.getProject(), aux.getAccepted(), aux.getReviewed()))
                .toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
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
                .filter(projectTeacher -> projectTeacher.getProject().getPhase().equals(PhaseName.REVIEWERS_PHASE.toString()))
                .map(aux -> new ProjectToHomePageVO(aux.getProject(), aux.getAccepted(), aux.getReviewed()))
                .toList();

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
