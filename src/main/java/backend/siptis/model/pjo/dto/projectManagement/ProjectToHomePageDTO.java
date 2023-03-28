package backend.siptis.model.pjo.dto.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectToHomePageDTO {
    private Long projectID;
    private String projectName;
    private List<String> tutors;
    private List<String> teachers;
    private List<String> students;
    private List<String> supervisors;
    private String moreInfo;
    private List<String> areas;
    private List<String> subAreas;
    private Boolean accepted;
    private Boolean reviewed;


    public ProjectToHomePageDTO(Project project, Boolean accepted, Boolean reviewed) {
        projectID = project.getId();
        projectName = project.getName();
        tutors = project.getTutors()
                .stream()
                .map(aux -> aux.getTutor().getUserInformation().getNames())
                .toList();
        teachers = project.getTeachers()
                .stream()
                .map(aux -> aux.getTeacher().getUserInformation().getNames())
                .toList();
        students = project.getStudents()
                .stream()
                .map(aux -> aux.getStudent().getUserInformation().getNames())
                .toList();
        supervisors = project.getSupervisors()
                .stream()
                .map(aux -> aux.getSupervisor().getUserInformation().getNames())
                .toList();
        moreInfo = "";
        areas = project.getAreas()
                .stream()
                .map(aux -> aux.getName())
                .toList();
        subAreas = project.getSubAreas()
                .stream()
                .map(aux -> aux.getName())
                .toList();
        this.accepted = accepted;
        this.reviewed = reviewed;
    }
}
