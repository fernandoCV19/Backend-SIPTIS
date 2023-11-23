package backend.siptis.model.pjo.dto.user_data;

import backend.siptis.model.entity.project_management.Area;
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
