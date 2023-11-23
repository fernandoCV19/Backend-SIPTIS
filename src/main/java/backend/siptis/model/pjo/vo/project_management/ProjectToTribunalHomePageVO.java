package backend.siptis.model.pjo.vo.project_management;

import backend.siptis.model.entity.project_management.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectToTribunalHomePageVO extends ProjectToHomePageVO {
    private DefenseVO defense;
    private Double defensePoints;

    public ProjectToTribunalHomePageVO(Project project, Double defensePoints, Boolean accepted, Boolean reviewed) {
        super(project, accepted, reviewed);
        defense = project.getDefense() != null ? new DefenseVO(project.getDefense()) : null;
        this.defensePoints = defensePoints;
    }
}
