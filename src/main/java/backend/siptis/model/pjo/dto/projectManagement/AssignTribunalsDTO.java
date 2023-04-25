package backend.siptis.model.pjo.dto.projectManagement;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AssignTribunalsDTO {
    private List<Long> tribunalsIds;
    private Long projectId;

    public AssignTribunalsDTO(Long[] ints, Long i) {
        tribunalsIds = new ArrayList<>(List.of(ints));
        projectId = i;
    }
}
