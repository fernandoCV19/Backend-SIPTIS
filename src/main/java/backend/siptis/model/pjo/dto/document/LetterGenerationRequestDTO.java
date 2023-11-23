package backend.siptis.model.pjo.dto.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterGenerationRequestDTO {
    private Long projectId;
    private Long userId;
}
