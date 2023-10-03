package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.Data;

import java.util.List;

@Data
public class InfoToReviewAProjectVO {

    private String projectName;
    private List<String> students;
    private List<ReviewShortInfoVO> otherReviews;


    public InfoToReviewAProjectVO(Project project, List<ReviewShortInfoVO> reviews) {
        projectName = project.getName();
        students = project.getStudents().stream().map(student -> student.getStudent().getUserInformation().getNames() + " " + student.getStudent().getUserInformation().getLastnames()).toList();
        otherReviews = reviews;
    }
}
