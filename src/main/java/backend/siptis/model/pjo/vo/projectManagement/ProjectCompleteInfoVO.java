package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Area;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.projectManagement.SubArea;
import lombok.Data;

import java.util.List;

@Data
public class ProjectCompleteInfoVO {

    private Long id;
    private String name;
    private String perfilPath;
    private String blueBookPath;
    private String projectPath;
    private String phase;
    private DefenseVO defense;
    private String modality;
    private Double score;
    private List<String> subAreas;
    private List<String> areas;
    private List<String> students;
    private List<String> teachers;
    private List<String> tutors;
    private List<String> supervisors;
    private List<String> tribunals;

    public ProjectCompleteInfoVO(Project project) {
        id = project.getId();
        name = project.getName();
        perfilPath = project.getPerfilPath();
        blueBookPath = project.getBlueBookPath();
        projectPath = project.getProjectPath();
        phase = project.getPhase();
        defense = new DefenseVO(project.getDefense());
        modality = project.getModality().toString();
        subAreas = project.getSubAreas() != null ? project.getSubAreas().stream().map(SubArea::getName).toList() : null;
        areas = project.getAreas() != null ? project.getAreas().stream().map(Area::getName).toList() : null;
        score = project.getTotalDefensePoints();
        students = project.getStudents() != null ? project.getStudents()
                .stream()
                .map(e -> e.getStudent().getUserInformation().getNames() + " " + e.getStudent().getUserInformation().getLastnames())
                .toList() : null;

        teachers = project.getTeachers() != null ? project.getTeachers()
                .stream()
                .map(e -> e.getTeacher().getUserInformation().getNames() + " " + e.getTeacher().getUserInformation().getLastnames())
                .toList() : null;

        tutors = project.getTutors() != null ? project.getTutors()
                .stream()
                .map(e -> e.getTutor().getUserInformation().getNames() + " " + e.getTutor().getUserInformation().getLastnames())
                .toList() : null;

        supervisors = project.getSupervisors() != null ? project.getSupervisors()
                .stream()
                .map(e -> e.getSupervisor().getUserInformation().getNames() + " " + e.getSupervisor().getUserInformation().getLastnames())
                .toList() : null;

        tribunals = project.getTribunals() != null ? project.getTribunals()
                .stream()
                .map(e -> e.getTribunal().getUserInformation().getNames() + " " + e.getTribunal().getUserInformation().getLastnames())
                .toList() : null;
    }
}
