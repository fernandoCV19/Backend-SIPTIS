package backend.siptis.service.report.projects;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectStudent;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.entity.user_data.UserCareer;
import backend.siptis.model.pjo.dto.project_management.ProjectAreaDTO;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.projects.generation_tools.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectReportServiceImpl implements ProjectReportService {

    private final ProjectRepository projectRepository;
    private final CloudManagementService cloud;

    @Override
    public ServiceAnswer getProjectTribunalReport() {
        List<Project> projects = projectRepository.findByTribunalsNotEmpty();
        String fileName = TribunalProjectReportTool.generateReport(projects);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getProjectCareerReport() {
        List<Project> allProjects = projectRepository.findAll();
        List<Project> sistemas = new ArrayList<>();
        List<Project> informatica = new ArrayList<>();
        List<Project> combined = new ArrayList<>();

        for (Project project : allProjects) {
            if (isCareer(project, backend.siptis.commons.UserCareer.SISTEMAS))
                sistemas.add(project);
            else if (isCareer(project, backend.siptis.commons.UserCareer.INFORMATICA))
                informatica.add(project);
            else combined.add(project);
        }

        String fileName = ProjectByCareerReportTool.generateReport(sistemas, informatica, combined);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getProjectAreaReport() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectAreaDTO> projectAreas = projectList.stream()
                .flatMap(project -> project.getAreas().stream()
                        .map(area -> new ProjectAreaDTO(project.getName(), area.getName())))
                .sorted(Comparator.comparing(ProjectAreaDTO::getArea))
                .collect(Collectors.toList());

        String fileName = ProjectByAreaReportTool.generateReport(projectAreas);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getProjectSubAreaReport() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectAreaDTO> projectSubAreas = projectList.stream()
                .flatMap(project -> project.getSubAreas().stream()
                        .map(subArea -> new ProjectAreaDTO(project.getName(), subArea.getName())))
                .sorted(Comparator.comparing(ProjectAreaDTO::getArea))
                .collect(Collectors.toList());

        String fileName = ProjectBySubAreaReportTool.generateReport(projectSubAreas);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }


    @Override
    public ServiceAnswer getProjectPhaseReport() {
        List<Project> projectList = projectRepository.findAll();
        projectList = projectList.stream()
                .sorted((si1, si2) -> si1.getPhase()
                        .compareTo(si2.getPhase())).collect(Collectors.toList());
        String fileName = ProjectByPhaseReportTool.generateReport(projectList);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    public ServiceAnswer getCompleteProjectReport() {
        List<Project> projects = projectRepository.findAll();
        String fileName = CompleteProjectReportTool.generateReport(projects);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getActiveAndInactiveProjects() {
        List<Project> projectList = projectRepository.findAll();
        projectList = projectList.stream()
                .sorted((p1, p2) -> p1.getState().getName()
                        .compareTo(p2.getState().getName())).toList();
        String fileName = ProjectsByStateReportTool.generateReport(projectList);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    private boolean isCareer(Project project, backend.siptis.commons.UserCareer compareCareer) {
        Collection<ProjectStudent> students = project.getStudents();
        boolean isCareer = true;

        for (ProjectStudent student : students) {
            Set<UserCareer> studentCareer = student.getStudent().getCareer();
            for (UserCareer career : studentCareer) {
                isCareer = isCareer && career.getName().equals(compareCareer.name());
            }
        }
        return isCareer;
    }
}
