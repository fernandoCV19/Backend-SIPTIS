package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.projectManagement.AssignTribunalsDTO;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;
import backend.siptis.model.pjo.dto.projectManagement.NewProjectDTO;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    ServiceAnswer createProject(NewProjectDTO dto);

    ServiceAnswer getProjects();

    ServiceAnswer getProjectInfo(Long id);

    ServiceAnswer getProjectsList(String search, Pageable pageable);

    ServiceAnswer getPaginatedCompletedProjects(int pageNumber, int pageSize);

    ServiceAnswer getPaginatedCompletedProjectsByName(int pageNumber, int pageSize, String name);

    ServiceAnswer getPaginatedCompletedProjectsByModality(int pageNumber, int pageSize, String modality);

    ServiceAnswer getPaginatedCompletedProjectsByArea(int pageNumber, int pageSize, String area);

    ServiceAnswer getPaginatedCompletedProjectsBySubArea(int pageNumber, int pageSize, String subArea);

    ServiceAnswer getPaginatedCompletedProjectsByFilters(int pageNumber, int pageSize, String name, String modality, String area, String subArea);

    ServiceAnswer getPresentations (Long idProyecto);

    ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer);

    ServiceAnswer getAllProjectInfo(Long idProject);

    ServiceAnswer getProjectInfoToAssignTribunals(Long idProject);

    ServiceAnswer assignTribunals(AssignTribunalsDTO assignTribunalsDTO);

    ServiceAnswer getSchedulesInfoToAssignADefense(Long projectId);

    ServiceAnswer addDefense(DefenseDTO defenseDTO);

    ServiceAnswer getProjectsToDefenseOrDefended(Long userId);

    ServiceAnswer getProjectsWithoutAndWithTribunals();

    ServiceAnswer getProjectsWithoutAndWithDefensePlace();

    ServiceAnswer getNumberOfProyectsByModalityAndCareer(Long id);

    ServiceAnswer getNumberProjectsByPeriodAndCareer(Long careerId);
}
