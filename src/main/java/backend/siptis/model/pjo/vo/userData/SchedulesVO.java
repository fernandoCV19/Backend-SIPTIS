package backend.siptis.model.pjo.vo.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchedulesVO {
    private Long id;
    private String name;
    private List<String> schedules;

    public SchedulesVO(ProjectStudent student) {
        SiptisUser studentUser = student.getStudent();
        this.id = studentUser.getId();
        this.name = studentUser.getUserInformation().getNames() + " " + studentUser.getUserInformation().getLastNames();
        this.schedules = studentUser.getAvailableSchedules()
                .stream()
                .map((schedule) -> schedule.getDay() + ":" + schedule.getHourStart() + " - " + schedule.getHourFinish())
                .toList();
    }

    public SchedulesVO(ProjectTribunal tribunal) {
        SiptisUser tribunalUser = tribunal.getTribunal();
        this.id = tribunalUser.getId();
        this.name = tribunalUser.getUserInformation().getNames() + " " + tribunalUser.getUserInformation().getLastNames();
        this.schedules = tribunalUser.getAvailableSchedules()
                .stream()
                .map((schedule) -> schedule.getDay() + ":" + schedule.getHourStart() + " - " + schedule.getHourFinish())
                .toList();
    }
}
