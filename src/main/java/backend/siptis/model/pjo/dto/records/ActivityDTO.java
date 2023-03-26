package backend.siptis.model.pjo.dto.records;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ActivityDTO {
    private String activityDescription;
    private Date activityDate;
}
