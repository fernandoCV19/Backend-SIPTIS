package backend.siptis.model.pjo.vo.project_management;

import backend.siptis.model.entity.project_management.Project;
import lombok.Data;

import java.util.List;

@Data
public class InfoToReviewAProjectVO {

    private String projectName;
    private List<String> students;
    private List<ReviewShortInfoVO> otherReviews;


    public InfoToReviewAProjectVO(Project project, List<ReviewShortInfoVO> reviews) {
        projectName = project.getName();
        students = project.getStudents().stream().map(student -> student.getStudent().getUserInformation().getNames() + " " + student.getStudent().getUserInformation().getLastNames()).toList();
        otherReviews = reviews;
    }
}
