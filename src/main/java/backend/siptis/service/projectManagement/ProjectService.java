package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
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

    ServiceAnswer getPaginatedCompletedProjectsByFilters(int pageNumber, int pageSize, String name, String period ,String modality, String area, String subArea);

    ServiceAnswer getProjectsWithAdvancedFilter (int pageNumber, int pageSize, String name, String period ,String modality, String area, String subarea, String studentNames, String studentLastNames, String tutorNames, String tutorLastNames);

    ServiceAnswer getPresentations(Long projectId);

    ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer);

    ServiceAnswer getAllProjectInfo(Long idProject);

    ServiceAnswer getProjectById(Long id);

    ServiceAnswer getProjectInfoToAssignTribunals(Long idProject);

    ServiceAnswer getInvolvedPeople(Long idProject);

    ServiceAnswer getSchedulesInfoToAssignADefense(Long projectId);

    ServiceAnswer getProjectsToDefenseOrDefended(Long userId);

    ServiceAnswer getProjectsWithoutAndWithTribunals();

    ServiceAnswer getProjectsWithoutAndWithDefensePlace();

    ServiceAnswer getNumberOfProjectsByModalityAndCareer(Long id);

    ServiceAnswer getNumberOfProjectsByAreaAndCareer(Long id);

    ServiceAnswer getNumberOfProjectsBySubAreaAndCareer(Long id);

    ServiceAnswer getNumberProjectsByPeriodAndCareer(Long careerId);

    ServiceAnswer getNumberProjectsByCareer(Long careerId);
}
