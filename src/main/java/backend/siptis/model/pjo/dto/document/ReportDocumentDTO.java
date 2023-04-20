package backend.siptis.model.pjo.dto.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDocumentDTO {
    private int reportNumber;
    private String title;
    private String postulant;
    private String tutor;
    private String teacher;
    private String description;
    //private MultipartFile tutorSignature;
    //private MultipartFile teacherSignature;
}
