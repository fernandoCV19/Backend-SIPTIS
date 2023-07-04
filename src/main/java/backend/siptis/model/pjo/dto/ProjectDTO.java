package backend.siptis.model.pjo.dto;

import backend.siptis.model.entity.projectManagement.Area;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ProjectDTO {
    Long id;
    String name;
    String perfil;
    String modality;
    Long modalityId;
    Set<Area> areas;
}
