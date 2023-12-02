package backend.siptis.service.report;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectStudent;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.entity.user_data.UserCareer;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.geneartion_tools.TribunalProjectReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectReportReportServiceImpl implements ProjectReportService {

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
        return null;
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