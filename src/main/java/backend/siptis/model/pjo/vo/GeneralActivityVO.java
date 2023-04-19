package backend.siptis.model.pjo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GeneralActivityVO {
    private long id;
    private String activityName;
    private String activityDescription;
    private Date activityDate;
}
