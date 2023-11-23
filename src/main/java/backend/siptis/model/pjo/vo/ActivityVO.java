package backend.siptis.model.pjo.vo;

import backend.siptis.model.entity.project_management.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityVO {
    private String activityName;
    private String activityDescription;
    private LocalDate activityDate;
    private Project project;
}
