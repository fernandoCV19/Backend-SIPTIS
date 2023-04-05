package backend.siptis.model.pjo.dto.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.Data;

@Data
public class ProjectToTribunalHomePageDTO extends ProjectToHomePageDTO {
    private DefenseDTO defense;
    private Double defensePoints;

    public ProjectToTribunalHomePageDTO(Project project, Double defensePoints, Boolean accepted, Boolean reviewed) {
        super(project, accepted, reviewed);
        defense = project.getDefense() != null?new DefenseDTO(project.getDefense()):null;
        this.defensePoints = defensePoints;
    }
}
