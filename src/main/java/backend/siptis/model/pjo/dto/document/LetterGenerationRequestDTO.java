package backend.siptis.model.pjo.dto.document;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterGenerationRequestDTO {
    @NotNull(message = "INVALID_PROJECT_ID")
    private Long projectId;
    @NotNull(message = "INVALID_USER_ID")
    private Long userId;
}
