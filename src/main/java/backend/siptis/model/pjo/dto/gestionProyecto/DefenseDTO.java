package backend.siptis.model.pjo.dto.gestionProyecto;

import backend.siptis.model.entity.gestionProyecto.Defense;
import lombok.Data;

@Data
public class DefenseDTO {
    private String place;
    private String hour;

    public DefenseDTO(Defense defense) {
        place = defense.getPlace();
        hour = defense.getHour();
    }
}
