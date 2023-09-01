package backend.siptis.model.pjo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralActivityVO {
    private long id;
    private String activityName;
    private String activityDescription;
    private Date activityDate;
}
