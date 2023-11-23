package backend.siptis.model.pjo.vo.project_management;

import backend.siptis.model.entity.defense_management.Defense;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DefenseVO {
    private Long projectId;
    private String projectName;
    private PlaceToDefenseVO place;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String substituteName;

    public DefenseVO(Defense defense) {
        projectId = defense.getProject().getId();
        projectName = defense.getProject().getName();
        place = new PlaceToDefenseVO(defense.getPlaceToDefense());
        date = defense.getDate();
        startTime = defense.getStartTime();
        endTime = defense.getEndTime();
        substituteName = defense.getSubstituteName();
    }
}
