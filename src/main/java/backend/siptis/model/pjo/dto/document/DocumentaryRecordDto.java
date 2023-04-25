package backend.siptis.model.pjo.dto.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentaryRecordDto {
    private boolean degree;
    private Long userId;
    private Long projectId;
    private String mention;
    private String consultants;
    private String summary;
    private String keyWords;
    private String defenseDate;
    private String pageQuantity;
}
