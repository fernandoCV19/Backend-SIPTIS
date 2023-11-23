package backend.siptis.model.pjo.vo.user_data;

import backend.siptis.model.entity.editors_and_reviewers.ProjectStudent;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllSchedulesVO {
    private List<SchedulesVO> students;
    private List<SchedulesVO> tribunals;

    public AllSchedulesVO(Collection<ProjectStudent> students, Collection<ProjectTribunal> tribunals) {
        this.students = students.stream().map(SchedulesVO::new).toList();
        this.tribunals = tribunals.stream().map(SchedulesVO::new).toList();
    }
}
