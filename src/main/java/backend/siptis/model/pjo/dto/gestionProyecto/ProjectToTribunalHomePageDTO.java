package backend.siptis.model.pjo.dto.gestionProyecto;

import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import lombok.Data;

@Data
public class ProjectToTribunalHomePageDTO extends ProjectToHomePageDTO {
    private DefenseDTO defense;
    private Double defensePoints;

    public ProjectToTribunalHomePageDTO(ProyectoGrado proyectoGrado, Double defensePoints, Boolean accepted, Boolean reviewed) {
        super(proyectoGrado, accepted, reviewed);
        defense = new DefenseDTO(proyectoGrado.getDefense());
        this.defensePoints = defensePoints;
    }
}
