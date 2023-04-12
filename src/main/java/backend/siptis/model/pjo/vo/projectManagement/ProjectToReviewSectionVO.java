package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public ProjectToReviewSectionVO(Project project, Boolean studentChanges,Integer numberOfDays) {
        students = project.getStudents().stream().map((studentProject) -> studentProject.getStudent().getUserInformation().getNames()).toList();
        projectName = project.getName();
        modality = project.getModality().getName();
        projectKey = project.getProjectPath();
        blueBookKey = project.getBlueBookPath();


        this.studentChanges = studentChanges;
        this.numberOfDays = numberOfDays;

    }
}
