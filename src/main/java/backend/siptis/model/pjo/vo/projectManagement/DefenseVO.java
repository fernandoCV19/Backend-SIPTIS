package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Defense;
import lombok.Data;

import java.util.Date;

@Data
public class DefenseVO {
    private String place;
    private Date defenseHour;

    public DefenseVO(Defense defense) {
        if (defense != null) {
            place = defense.getPlaceToDefense().getName();
            defenseHour = defense.getDate();
        }
    }
}
