package backend.siptis.model.pjo.dto.semester;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSemesterInfoDTO {
    private Long id;
    private String endDate;
    private String startDate;
    private String period;
}
