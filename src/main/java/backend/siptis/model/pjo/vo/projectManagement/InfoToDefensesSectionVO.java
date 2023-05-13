package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import lombok.Data;

import java.util.List;

@Data
public class InfoToDefensesSectionVO {

    private List<ProjectCompleteInfoVO> defendedProjects;
    private List<ProjectCompleteInfoVO> projectsToDefense;

    public InfoToDefensesSectionVO(List<Project> projectsToDefend, List<Project> projectsDefended) {
        defendedProjects = projectsDefended.stream().map(ProjectCompleteInfoVO::new).toList();
        projectsToDefense = projectsToDefend.stream().map(ProjectCompleteInfoVO::new).toList();
    }
}
