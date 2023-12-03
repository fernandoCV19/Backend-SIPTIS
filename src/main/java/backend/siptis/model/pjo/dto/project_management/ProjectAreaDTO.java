package backend.siptis.model.pjo.dto.project_management;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectAreaDTO {
    private String project;
    private String area;
}
