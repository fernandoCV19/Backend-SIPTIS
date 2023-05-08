package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Area;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.projectManagement.SubArea;
import lombok.Data;

import java.util.List;

@Data
public class ProjectInfoToAssignTribunalsVO {

    private String name;
    private List<String> students;
    private List<String> areas;
    private List<String> subAreas;

    public ProjectInfoToAssignTribunalsVO(Project project) {
        name = project.getName();
        students = project.getStudents().stream().map(e -> e.getStudent().getUserInformation().getNames() + " " + e.getStudent().getUserInformation().getLastnames()).toList();
        areas = project.getAreas().stream().map(Area::getName).toList();
        subAreas = project.getSubAreas().stream().map(SubArea::getName).toList();
    }
}
