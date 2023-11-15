package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.Data;

import java.util.List;

@Data
public class InvolvedPeopleVO {

    private String name;
    private List<String> students;
    private List<String> tribunals;
    private List<String> supervisors;
    private List<String> teachers;
    private List<String> tutors;

    public InvolvedPeopleVO(Project project) {
        name = project.getName();
        students = project.getStudents().stream().map(e -> e.getStudent().getUserInformation().getNames() + " " + e.getStudent().getUserInformation().getLastNames()).toList();

        tribunals = project.getTribunals().stream().map(e -> e.getTribunal().getUserInformation().getNames() + " " + e.getTribunal().getUserInformation().getLastNames()).toList();

        supervisors = project.getSupervisors().stream().map(e -> e.getSupervisor().getUserInformation().getNames() + " " + e.getSupervisor().getUserInformation().getLastNames()).toList();

        teachers = project.getTeachers().stream().map(e -> e.getTeacher().getUserInformation().getNames() + " " + e.getTeacher().getUserInformation().getLastNames()).toList();

        tutors = project.getTutors().stream().map(e -> e.getTutor().getUserInformation().getNames() + " " + e.getTutor().getUserInformation().getLastNames()).toList();
    }
}
