package backend.siptis.model.pjo.dto.projectManagement;

import backend.siptis.model.entity.projectManagement.Defense;
import lombok.Data;

@Data
public class DefenseDTO {
    private String place;
    private String defenseHour;

    public DefenseDTO(Defense defense) {
        place = defense.getPlace();
        defenseHour = defense.getDefenseHour();
    }
}
