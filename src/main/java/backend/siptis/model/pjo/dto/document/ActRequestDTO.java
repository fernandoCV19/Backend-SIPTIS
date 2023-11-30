package backend.siptis.model.pjo.dto.document;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActRequestDTO {
    @NotNull(message = "INVALID_PROJECT_ID")
    private Long projectId;
    @NotNull(message = "INVALID_USER_NAME")
    private String presidentName;
    @NotNull(message = "INVALID_USER_NAME")
    private String deanName;
}
