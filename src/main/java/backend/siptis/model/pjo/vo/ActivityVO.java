package backend.siptis.model.pjo.vo;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ActivityVO {
    private String activityName;
    private String activityDescription;
    private Date activityDate;
    private Project project;
}
