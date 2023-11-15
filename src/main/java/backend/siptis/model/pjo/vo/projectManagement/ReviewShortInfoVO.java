package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Review;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewShortInfoVO {
    private String documentPath;
    private String commentary;
    private String user;
    private LocalDateTime date;

    public ReviewShortInfoVO(Review review) {
        documentPath = review.getDocumentPath();
        commentary = review.getCommentary();
        user = review.getSiptisUser().getUserInformation().getNames() + " " + review.getSiptisUser().getUserInformation().getLastnames();
        date = review.getDate();
    }
}
