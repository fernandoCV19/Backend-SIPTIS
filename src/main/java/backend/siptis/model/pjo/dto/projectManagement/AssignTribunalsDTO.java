package backend.siptis.model.pjo.dto.projectManagement;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignTribunalsDTO {
    private List<Long> tribunalsIds;
    private Long projectId;
}
