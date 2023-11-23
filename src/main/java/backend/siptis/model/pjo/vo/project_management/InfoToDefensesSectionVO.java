package backend.siptis.model.pjo.vo.project_management;

import backend.siptis.model.entity.project_management.Project;
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
