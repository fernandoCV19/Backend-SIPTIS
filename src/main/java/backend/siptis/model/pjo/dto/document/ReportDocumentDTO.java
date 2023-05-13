package backend.siptis.model.pjo.dto.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDocumentDTO {
    private long userId;
    private long projectId;
    private String description;
    private String shortDescription;
    //private MultipartFile tutorSignature;
    //private MultipartFile teacherSignature;
}
