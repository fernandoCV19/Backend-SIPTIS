package backend.siptis.model.pjo.dto.project_management;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class DefenseDTO {
    private Long placeId;
    private Long projectId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String substituteName;
}
