package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Defense;
import lombok.Data;

@Data
public class DefenseVO {
    private String place;
    private String defenseHour;

    public DefenseVO(Defense defense) {
        if (defense != null) {
            place = defense.getPlace();
            defenseHour = defense.getDefenseHour();
        }
    }
}
