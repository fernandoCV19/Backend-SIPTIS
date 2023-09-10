package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Defense;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DefenseVO {
    private Long projectId;
    private Long placeId;
    private Long directorId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public DefenseVO(Defense defense) {
        projectId = defense.getId();
        placeId = defense.getPlaceToDefense().getId();
        directorId = defense.getDirector().getId();
        date = defense.getDate();
        startTime = defense.getStartTime();
        endTime = defense.getEndTime();
    }
}
