package backend.siptis.model.pjo.dto.notifications;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GeneralActivityDTO {
    private String generalActivityName;
    private String activityDescription;
    private Date activityDate;
}
