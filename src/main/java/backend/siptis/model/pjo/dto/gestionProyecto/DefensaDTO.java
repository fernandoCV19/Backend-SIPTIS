package backend.siptis.model.pjo.dto.gestionProyecto;

import backend.siptis.model.entity.gestionProyecto.Defensa;
import lombok.Data;

@Data
public class DefensaDTO {
    private String lugar;
    private String hora;

    public DefensaDTO(Defensa defensa) {
        lugar = defensa.getLugarDefensa();
        hora = defensa.getHoraDefensa();
    }
}
