package backend.siptis.model.pjo.dto.editorsAndReviewers;

import lombok.Data;

@Data
public class ReviewADefenseDTO {

    private Long project;
    private Long tribunal;
    private Double points;

    public ReviewADefenseDTO(Long project, Long tribunal, Double points) {
        this.project = project;
        this.tribunal = tribunal;
        this.points = points;
    }
}
