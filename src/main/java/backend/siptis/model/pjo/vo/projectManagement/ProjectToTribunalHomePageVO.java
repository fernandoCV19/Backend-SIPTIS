package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.Data;

@Data
public class    ProjectToTribunalHomePageVO extends ProjectToHomePageVO {
    private DefenseVO defense;
    private Double defensePoints;

    public ProjectToTribunalHomePageVO(Project project, Double defensePoints, Boolean accepted, Boolean reviewed) {
        super(project, accepted, reviewed);
        defense = project.getDefense() != null?new DefenseVO(project.getDefense()):null;
        this.defensePoints = defensePoints;
    }
}
