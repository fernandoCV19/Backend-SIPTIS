package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectToReviewSectionVO {
    private List<String> students;
    private String projectName;
    private String modality;
    private String projectKey;
    private String blueBookKey;
    private Boolean studentChanges;
    private Integer numberOfDays;
    private Boolean reviewed;

    public ProjectToReviewSectionVO(Project project, Boolean studentChanges,Integer numberOfDays, Boolean reviewed) {
        students = project.getStudents().stream().map((studentProject) -> studentProject.getStudent().getUserInformation().getNames() + " " + studentProject.getStudent().getUserInformation().getLastnames()).toList();
        projectName = project.getName();
        modality = project.getModality().getName();
        projectKey = project.getProjectPath();
        blueBookKey = project.getBlueBookPath();

        this.reviewed = reviewed;
        this.studentChanges = studentChanges;
        this.numberOfDays = numberOfDays;

    }
}
