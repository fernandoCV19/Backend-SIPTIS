package backend.siptis.model.pjo.vo.project_management;

import backend.siptis.model.entity.project_management.Project;
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

    public ProjectToReviewSectionVO(Project project, Boolean studentChanges, Integer numberOfDays, Boolean reviewed) {
        students = project.getStudents().stream().map(studentProject -> studentProject.getStudent().getUserInformation().getNames() + " " + studentProject.getStudent().getUserInformation().getLastNames()).toList();
        projectName = project.getName();
        modality = project.getModality().getName();
        projectKey = project.getProjectPath();
        blueBookKey = project.getBlueBookPath();

        this.reviewed = reviewed;
        this.studentChanges = studentChanges;
        this.numberOfDays = numberOfDays;

    }
}
