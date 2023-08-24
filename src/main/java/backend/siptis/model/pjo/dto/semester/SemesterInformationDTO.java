package backend.siptis.model.pjo.dto.semester;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SemesterInformationDTO {
    private Date startDate;
    private Date endDate;
}
