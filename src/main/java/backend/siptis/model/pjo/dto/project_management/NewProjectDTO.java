package backend.siptis.model.pjo.dto.project_management;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewProjectDTO {

    @NotEmpty(message = "NAME_CANNOT_BE_NULL")
    @Size(min = 2, message = "INVALID_NAME_LENGTH")
    @Size(max = 50, message = "INVALID_NAME_LENGTH")
    private String name;
    @NotNull(message = "INVALID_NAME")
    private Long modalityId;

    @NotNull(message = "INVALID_PROJECT_STUDENTS_VALUE")
    @NotEmpty(message = "INVALID_PROJECT_STUDENTS_VALUE")
    private List<Long> studentsId;
    @NotNull(message = "INVALID_PROJECT_TUTOR_VALUE")
    @NotEmpty(message = "INVALID_PROJECT_TUTOR_VALUE")
    private List<Long> tutorsId;

    private List<Long> supervisorsId;
    @NotNull(message = "INVALID_PROJECT_TEACHER_VALUE")
    @NotEmpty(message = "INVALID_PROJECT_TEACHER_VALUE")
    private List<Long> teachersId;
    @NotNull(message = "INVALID_PROJECT_AREA_VALUE")
    @NotEmpty(message = "INVALID_PROJECT_AREA_VALUE")
    private List<Long> areasId;
    @NotNull(message = "INVALID_PROJECT_SUB_AREA_VALUE")
    @NotEmpty(message = "INVALID_PROJECT_SUB_AREA_VALUE")
    private List<Long> subAreasId;
}
