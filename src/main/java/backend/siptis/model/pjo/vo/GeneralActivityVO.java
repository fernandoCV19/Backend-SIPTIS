package backend.siptis.model.pjo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralActivityVO {
    private long id;
    private String activityName;
    private String activityDescription;
    private LocalDate activityDate;
}
