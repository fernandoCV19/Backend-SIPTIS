package BackendSIPTIS.model.pjo.dto.gestionProyecto;

import BackendSIPTIS.model.entity.gestionProyecto.Defensa;
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
