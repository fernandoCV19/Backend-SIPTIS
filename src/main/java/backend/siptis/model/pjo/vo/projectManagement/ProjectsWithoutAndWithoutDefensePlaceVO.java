package backend.siptis.model.pjo.vo.projectManagement;

import lombok.Data;

import java.util.List;

@Data
public class ProjectsWithoutAndWithoutDefensePlaceVO {

    private List<ProjectCompleteInfoVO> withDefense;
    private List<ProjectCompleteInfoVO> withoutDefense;

    public ProjectsWithoutAndWithoutDefensePlaceVO(List<ProjectCompleteInfoVO> withDefense, List<ProjectCompleteInfoVO> withoutDefense) {
        this.withoutDefense = withoutDefense;
        this.withDefense = withDefense;
    }
}
