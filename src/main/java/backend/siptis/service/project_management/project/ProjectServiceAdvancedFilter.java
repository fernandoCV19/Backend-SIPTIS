package backend.siptis.service.project_management.project;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectStudent;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTutor;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.repository.project_management.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProjectServiceAdvancedFilter {

    private final ProjectRepository projectRepository;

    public ServiceAnswer getProjectsWithAdvancedFilter(int pageNumber, int pageSize, String name, String period, String modality, String area, String subarea, String student, String tutor) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.advancedFilter(name, period, modality, area, subarea, student, tutor, pageable);
        if (projectPage.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();

        List<Project> projects = projectPage.getContent();
        int total = projectPage.getTotalPages();
        Map<String, Object> totalResponse = new HashMap<>();
        totalResponse.put("totalPages", total);
        totalResponse.put("page", pageNumber);

        List<Map<String, Object>> foundProjects = new ArrayList<>();

        for (Project p : projects) {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("id", p.getId());
            jsonMap.put("projectName", p.getName());
            jsonMap.put("period", p.getPeriod());
            jsonMap.put("modality", p.getModality().getName());
            jsonMap.put("summarySheetPath", p.getSummarySheetPath());
            jsonMap.put("areas", p.getAreas().toArray());
            jsonMap.put("subAreas", p.getSubAreas().toArray());

            List<ProjectStudent> projectStudents = p.getStudents().stream().toList();
            List<String> studentsNames = new ArrayList<>();
            for (ProjectStudent ps : projectStudents) {
                String names = ps.getStudent().getUserInformation().getNames().trim();
                String lastnames = ps.getStudent().getUserInformation().getLastNames().trim();
                studentsNames.add(names + " " + lastnames);
            }

            jsonMap.put("students", studentsNames);

            List<ProjectTutor> projectTutors = p.getTutors().stream().toList();
            List<String> tutorsNames = new ArrayList<>();
            for (ProjectTutor ps : projectTutors) {
                String names = ps.getTutor().getUserInformation().getNames().trim();
                String lastnames = ps.getTutor().getUserInformation().getLastNames().trim();
                tutorsNames.add(names + " " + lastnames);
            }
            jsonMap.put("tutors", tutorsNames);
            foundProjects.add(jsonMap);
        }
        totalResponse.put("content", foundProjects);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(totalResponse).build();
    }
}
