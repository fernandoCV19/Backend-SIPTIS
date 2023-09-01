package backend.siptis.model.pjo.dto.projectManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectOverviewDTO {
    private String projectName;
    private String modality;
    private String blueBookPath;
    private String projectPath;
    private String perfilPath;

    private List<String> areas;
    private List<String> subareas;

    //private Collection<ProjectTutor> tutors;
}