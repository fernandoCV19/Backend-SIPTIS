package backend.siptis.model.pjo.dto.notifications;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GeneralActivityDTO {
    private String activityName;
    private String activityDescription;
    private LocalDate activityDate;
}
