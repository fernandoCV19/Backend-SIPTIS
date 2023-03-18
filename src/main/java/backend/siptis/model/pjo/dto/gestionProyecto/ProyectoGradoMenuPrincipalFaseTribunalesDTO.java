package backend.siptis.model.pjo.dto.gestionProyecto;

import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import lombok.Data;

@Data
public class ProyectoGradoMenuPrincipalFaseTribunalesDTO extends ProyectoGradoMenuPrincipalDTO{
    private DefensaDTO defensa;
    private Double notaDefensa;

    public ProyectoGradoMenuPrincipalFaseTribunalesDTO(ProyectoGrado proyectoGrado, Double notaDefensa, Boolean aceptado, Boolean revisado) {
        super(proyectoGrado, aceptado, revisado);
        defensa = new DefensaDTO(proyectoGrado.getDefensa());
        this.notaDefensa = notaDefensa;
    }
}
