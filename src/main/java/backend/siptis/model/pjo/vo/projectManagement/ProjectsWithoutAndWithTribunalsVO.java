package backend.siptis.model.pjo.vo.projectManagement;

import lombok.Data;

import java.util.List;

@Data
public class ProjectsWithoutAndWithTribunalsVO {
    private List<ProjectCompleteInfoVO> withTribunals;
    private List<ProjectCompleteInfoVO> withoutTribunals;

    public ProjectsWithoutAndWithTribunalsVO(List<ProjectCompleteInfoVO> withTribunals, List<ProjectCompleteInfoVO> withoutTribunals) {
        this.withoutTribunals = withoutTribunals;
        this.withTribunals = withTribunals;
    }
}
