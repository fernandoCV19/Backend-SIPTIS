package backend.siptis.model.pjo.vo.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.userData.UserArea;
import lombok.Data;

import java.util.List;

@Data
public class TribunalInfoToAssignSection {
    private Long id;
    private String name;
    private List<String> areas;
    private List<String> projectsNames;
    private Integer numberOfProjects;

    public TribunalInfoToAssignSection(SiptisUser user) {
        id = user.getId();
        name = user.getUserInformation().getNames() + " " + user.getUserInformation().getLastNames();
        areas = user.getAreas().stream().map(UserArea::getName).toList();
        numberOfProjects = user.getTribunalOf().size();
        projectsNames = user.getTribunalOf().stream().map(projectTribunal -> projectTribunal.getProject().getName()).toList();
    }
}
